package by.training.lakes_paradise.db.mysql;

import by.training.lakes_paradise.db.ConnectionDB;
import by.training.lakes_paradise.db.dao.ImageDao;
import by.training.lakes_paradise.db.entity.Image;
import by.training.lakes_paradise.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for realization working with "homesteads" table.
 */
public class ImageDaoRealization extends BaseDaoRealization
        implements ImageDao {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ProfileDaoRealization.class);

    /**
     * String for notification about problems with ResultSet.
     */
    private static final String CLOSE_RESULT_SET_EXCEPTION
            = "Impossible to close ResultSet.";
    /**
     * String for notification about problems with Statement.
     */
    private static final String CLOSE_STATEMENT_EXCEPTION
            = "Impossible to close Statement.";

    /**
     * String for notification about problems with SQL.
     */
    private static final String SQL_EXCEPTION
            = "Some exception connected with SQL.";

    /**
     * Point to the third element in SQL query.
     */
    private static final int THIRD_ELEMENT_IN_SQL_QUERY = 3;

    /**
     * Script insert new object into the table images.
     */
    private static final String SQL_SCRIPT_INSERT_DATA_INTO_TABLE
            = "insert into images (image, home_id) values (?, ?)";

    /**
     * Script gets all objects from table images by homestead id.
     */
    private static final String SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_HOME_ID
            = "select id, image from images where home_id = (?)";

    /**
     * Script gets all objects from table images by id.
     */
    private static final String SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_ID
            = "select image, home_id from images where id = (?)";

    /**
     * Script updates object in table images.
     */
    private static final String SQL_SCRIPT_UPDATE_DATA_IN_TABLE
            = "update images set image = ?, home_id = ? where id = (?)";

    /**
     *
     *
     * @param homeId - id of homestead
     * @return
     * @throws PersistentException
     */
    @Override
    public List<Image> readImagesByHomeId(Integer homeId)
            throws PersistentException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Image> images = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(
                    SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_HOME_ID);
            statement.setInt(1, homeId);
            resultSet = statement.executeQuery();
            Image image = null;

            while (resultSet.next()) {
                image = new Image();
                image.setId(resultSet.getInt(1));
                image.setImage(resultSet.getBlob(2));

                images.add(image);
            }

            return images;
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION);
            throw new PersistentException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(CLOSE_STATEMENT_EXCEPTION);
            }
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error(CLOSE_RESULT_SET_EXCEPTION);
            }
        }
    }

    /**
     *
     *
     * @param entity - new object
     * @return
     * @throws PersistentException
     */
    @Override
    public Integer create(Image entity) throws PersistentException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(
                    SQL_SCRIPT_INSERT_DATA_INTO_TABLE,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setBlob(1, entity.getImage());
            statement.setInt(2, entity.getHomeId());
            statement.execute();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                LOGGER.error("There is no autoincremented index after trying"
                        + " to add record into table `profiles`");
                throw new PersistentException();
            }
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION);
            throw new PersistentException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(CLOSE_STATEMENT_EXCEPTION);
            }
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error(CLOSE_RESULT_SET_EXCEPTION);
            }
        }
    }

    /**
     *
     *
     * @param id - id of object
     * @return
     * @throws PersistentException
     */
    @Override
    public Image read(Integer id) throws PersistentException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(
                    SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Image image = null;

            while (resultSet.next()) {
                image = new Image();
                image.setImage(resultSet.getBlob(1));
                image.setHomeId(resultSet.getInt(2));
            }

            return image;
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION);
            throw new PersistentException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(CLOSE_STATEMENT_EXCEPTION);
            }
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error(CLOSE_RESULT_SET_EXCEPTION);
            }
        }
    }

    /**
     *
     *
     * @param entity - updated object
     * @throws PersistentException
     */
    @Override
    public void update(Image entity) throws PersistentException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(
                    SQL_SCRIPT_UPDATE_DATA_IN_TABLE);
            statement.setBlob(1, entity.getImage());
            statement.setInt(2, entity.getHomeId());
            statement.setInt(THIRD_ELEMENT_IN_SQL_QUERY, entity.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION);
            throw new PersistentException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(CLOSE_STATEMENT_EXCEPTION);
            }
        }
    }

    /**
     *
     *
     * @param id - id of object for deletion
     * @throws PersistentException
     */
    @Override
    public void delete(Integer id) throws PersistentException {
        PreparedStatement statement = null;

        try {
            statement = delete("images", id);
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION);
            throw new PersistentException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(CLOSE_STATEMENT_EXCEPTION);
            }
        }
    }

    /**
     *
     *
     * @param homeId
     * @throws PersistentException
     */
    @Override
    public void deleteImagesByHomeId(Integer homeId)
            throws PersistentException {
        String sql = "delete from images where home_id = (?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, homeId);

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION);
            throw new PersistentException(e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                LOGGER.error(CLOSE_STATEMENT_EXCEPTION);
            }
        }
    }
}
