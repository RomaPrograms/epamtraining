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
     *
     *
     * @param id - id of image
     * @return
     * @throws PersistentException
     */
    @Override
    public List<Image> readImagesById(Integer id) throws PersistentException {
        String sql = "select image, id_home from images"
                + " where id = (?)";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Image> images = new ArrayList<>();

        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Image image = null;

            while (resultSet.next()) {
                image = new Image();
                image.setImage(resultSet.getBlob(1));
                image.setIdHome(resultSet.getInt(2));

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
        String sql = "insert into images (image, id_home) values"
                + " (?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setBlob(1, entity.getImage());
            statement.setInt(2, entity.getIdHome());
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
        String sql = "select image, id_home from images"
                + " where id = (?)";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Image image = null;

            while (resultSet.next()) {
                image = new Image();
                image.setImage(resultSet.getBlob(1));
                image.setIdHome(resultSet.getInt(2));
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
        String sql = "update images set image = ?, id_home = ?"
                + " where id = (?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setBlob(1, entity.getImage());
            statement.setInt(2, entity.getIdHome());
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
     * @param idHome
     * @throws PersistentException
     */
    @Override
    public void deleteImagesByIdHome(Integer idHome)
            throws PersistentException {
        String sql = "delete from images where id_home = (?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idHome);

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
