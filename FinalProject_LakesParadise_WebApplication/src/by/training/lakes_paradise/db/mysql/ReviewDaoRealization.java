package by.training.lakes_paradise.db.mysql;

import by.training.lakes_paradise.db.dao.ReviewDao;
import by.training.lakes_paradise.db.entity.Review;
import by.training.lakes_paradise.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for realization working with "reviews" table.
 */
public class ReviewDaoRealization extends BaseDaoRealization
        implements ReviewDao {
    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ReviewDaoRealization.class);

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
     * Point to the forth element in SQL query.
     */
    private static final int FORTH_ELEMENT_IN_SQL_QUERY = 4;
    /**
     * Point to the fifth element in SQL query.
     */
    private static final int FIFTH_ELEMENT_IN_SQL_QUERY = 5;

    /**
     * Script insert new object into the table reviews.
     */
    private static final String SQL_SCRIPT_INSERT_DATA_INTO_TABLE
            = "insert into reviews (text, user_name, date_of_comment, home_id)"
            + " values (?, ?, ?, ?)";

    /**
     * Script gets all objects from table reviews by homestead id.
     */
    private static final String SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_HOME_ID
            = "select id, text, user_name, date_of_comment, home_id from "
            + "reviews where home_id = (?) order by date_of_comment DESC";

    /**
     * Script gets all objects from table reviews by id.
     */
    private static final String SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_ID
            = "select id, text, user_name, date_of_comment, home_id from"
            + " reviews where id = (?)";

    /**
     * Script updates object in table reviews.
     */
    private static final String SQL_SCRIPT_UPDATE_DATA_IN_TABLE
            = "update reviews set text = ?, user_name = ?, date_of_comment = ?,"
            + " home_id = ? where id = ?";

    /**
     * Method that searches all reviews by id of homestead in "orders" table.
     *
     * @param homeId - id of homestead
     * @return list with images
     * @throws PersistentException - exception with searching in review table by
     * homestead id
     */
    @Override
    public List<Review> readByHomeId(final Integer homeId)
            throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        List<Review> reviews = new ArrayList<>();

        try {
            statement = getConnection().prepareStatement(
                    SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_HOME_ID);
            statement.setInt(1, homeId);
            resultSet = statement.executeQuery();
            Review review = null;

            while (resultSet.next()) {
                review = new Review();
                review.setId(resultSet.getInt("id"));
                review.setText(resultSet.getString("text"));
                review.setUserName(resultSet.getString("user_name"));
                Date date = resultSet.getDate("date_of_comment");
                review.setDateOfComment(new java.util.Date(date.getTime()));
                review.setHomesteadId(resultSet.getInt("home_id"));

                reviews.add(review);
            }

            return reviews;
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
     * Method deletes reviews by id of homestead in "orders" table..
     *
     * @param homeId - id of homestead
     * @throws PersistentException - exception with deleting in review table
     */
    @Override
    public void deleteByHomeId(final Integer homeId)
            throws PersistentException {
        String sql = "delete from reviews where home_id = (?)";
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(sql);
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

    /**
     * Method adds new review to "reviews" table.
     *
     * @param review - new object
     * @return - id of new object in database
     * @throws PersistentException - exception with adding object to database
     */
    @Override
    public Integer create(final Review review) throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SCRIPT_INSERT_DATA_INTO_TABLE,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, review.getText());
            statement.setString(2, review.getUserName());
            statement.setDate(THIRD_ELEMENT_IN_SQL_QUERY,
                    new Date(review.getDateOfComment().getTime()));
            statement.setInt(FORTH_ELEMENT_IN_SQL_QUERY,
                    review.getHomesteadId());
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
     * Method reads object to "reviews" table.
     *
     * @param id - id of object
     * @return object which was read
     * @throws PersistentException - exception with reading object from database
     */
    @Override
    public Review read(final Integer id) throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = getConnection().prepareStatement(
                    SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Review review = null;

            while (resultSet.next()) {
                review = new Review();
                review.setId(id);
                review.setText(resultSet.getString("text"));
                review.setUserName(resultSet.getString("user_name"));
                Date date = resultSet.getDate("date_of_comment");
                review.setDateOfComment(new java.util.Date(date.getTime()));
                review.setHomesteadId(resultSet.getInt("home_id"));
            }

            return review;
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
     * Method that updates from "reviews" table.
     *
     * @param review - updated reviews
     * @throws PersistentException - exception with updating in review table
     */
    @Override
    public void update(final Review review) throws PersistentException {
        PreparedStatement statement = null;

        try {
            statement = getConnection().prepareStatement(
                    SQL_SCRIPT_UPDATE_DATA_IN_TABLE);
            statement.setString(1, review.getText());
            statement.setString(2, review.getUserName());
            statement.setDate(THIRD_ELEMENT_IN_SQL_QUERY,
                    new Date(review.getDateOfComment().getTime()));
            statement.setInt(FORTH_ELEMENT_IN_SQL_QUERY,
                    review.getHomesteadId());
            statement.setInt(FIFTH_ELEMENT_IN_SQL_QUERY, review.getId());
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
     * Method deletes review from "reviews" table by review id.
     *
     * @param id - id of object for deletion
     */
    @Override
    public void delete(final Integer id) throws PersistentException {
        PreparedStatement statement = null;

        try {
            statement = delete("reviews", id);
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
