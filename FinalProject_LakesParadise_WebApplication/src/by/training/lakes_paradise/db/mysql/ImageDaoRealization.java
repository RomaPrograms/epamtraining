package by.training.lakes_paradise.db.mysql;

import by.training.lakes_paradise.db.dao.ImageDao;
import by.training.lakes_paradise.db.entity.Image;
import by.training.lakes_paradise.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
            = LogManager.getLogger(ImageDaoRealization.class);

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
            = "insert into images (pathToImage, home_id) values (?, ?)";

    /**
     * Script gets all objects from table images by homestead id.
     */
    private static final String SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_HOME_ID
            = "select id, pathToImage from images where home_id = (?)";

    /**
     * Script gets all objects from table images by id.
     */
    private static final String SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_ID
            = "select pathToImage, home_id from images where id = (?)";

    /**
     * Script updates object in table images.
     */
    private static final String SQL_SCRIPT_UPDATE_DATA_IN_TABLE
            = "update images set pathToImage = ?, home_id = ? where id = (?)";

    /**
     * Method that searches all images by id of homestead.
     *
     * @param homeId - id of homestead
     * @return list with images
     * @throws PersistentException - exception with searching in database
     */
    @Override
    public List<Image> readByHomeId(final Integer homeId)
            throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Image> images = new ArrayList<>();

        try {
            statement = getConnection().prepareStatement(
                    SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_HOME_ID);
            statement.setInt(1, homeId);
            resultSet = statement.executeQuery();
            Image image;

            while (resultSet.next()) {
                image = new Image();
                image.setId(
                        resultSet.getInt(1));
                image.setPathToImage(
                        resultSet.getString(2));
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
     * Method adds images to database.
     *
     * @param image - new image
     * @return number if methods ended successfully
     * @throws PersistentException - exception with creating images
     */
    @Override
    public Integer create(final Image image) throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SCRIPT_INSERT_DATA_INTO_TABLE,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(
                    1, image.getPathToImage());
            statement.setInt(
                    2, image.getHomesteadId());
            statement.execute();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                LOGGER.error("There is no autoincremented index after trying"
                        + " to create record into table `profiles`");
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
     * Method searches images by id.
     *
     * @param id - id of image
     * @return image
     * @throws PersistentException - exception with reading object from images
     *                             table
     */
    @Override
    public Image read(final Integer id) throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Image image = null;

            while (resultSet.next()) {
                image = new Image();
                image.setPathToImage(
                        resultSet.getString(1));
                image.setHomesteadId(
                        resultSet.getInt(2));
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
     * Method that updates images.
     *
     * @param image - updated image
     * @throws PersistentException - exception with updating in image table
     */
    @Override
    public void update(final Image image) throws PersistentException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SCRIPT_UPDATE_DATA_IN_TABLE);
            statement.setString(
                    1, image.getPathToImage());
            statement.setInt(
                    2, image.getHomesteadId());
            statement.setInt(
                    THIRD_ELEMENT_IN_SQL_QUERY, image.getId());

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
     * Methods that deletes images by id.
     *
     * @param id - id of image for deletion
     * @throws PersistentException - exception with deleting in image table
     *                             by id
     */
    @Override
    public void delete(final Integer id) throws PersistentException {
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
}
