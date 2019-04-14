package by.training.lakes_paradise.db.mysql;

import by.training.lakes_paradise.db.ConnectionDB;
import by.training.lakes_paradise.db.dao.ProfileDao;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for realization working with "profiles" table.
 */
public class ProfileDaoRealization implements ProfileDao {

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
     * Point to the forth element in SQL query.
     */
    private static final int FORTH_ELEMENT_IN_SQL_QUERY = 4;

    /**
     * Method returns profile of user by login and password.
     *
     * @param login    - login of user
     * @param password - profile of user
     * @return profile of user
     */
    @Override
    public Profile read(final String login, final String password)
            throws PersistentException {
        String sql = "select id, login, password, orders from profiles"
                + " where login = (?) and password = (?)";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            Profile profile = null;

            while (resultSet.next()) {
                profile = new Profile();
                profile.setId(resultSet.getInt("id"));
                profile.setLogin(resultSet.getString("login"));
                profile.setPassword(resultSet.getString("password"));
                profile.setOrders(resultSet.getInt("orders"));
            }

            return profile;
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
     * Method reads all objects from "profiles" table.
     *
     * @return objects from "profiles" table
     */
    @Override
    public List<Profile> read() throws PersistentException {
        String sql = "select id, login, password, orders from profiles";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            List<Profile> profiles = new ArrayList<>();
            Profile profile = null;

            while (resultSet.next()) {
                profile = new Profile();
                profile.setId(resultSet.getInt("id"));
                profile.setLogin(resultSet.getString("login"));
                profile.setPassword(resultSet.getString("password"));
                profile.setOrders(resultSet.getInt("orders"));

                profiles.add(profile);
            }

            return profiles;
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
     * Method adds new object to database.
     *
     * @param profile - new object
     * @return - id of new object in database
     * @throws PersistentException - exception with adding object to database
     */
    @Override
    public Integer create(final Profile profile) throws PersistentException {
        String sql = "insert into profiles (id, login, password, orders) values"
                + " (?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, profile.getId());
            statement.setString(2, profile.getLogin());
            statement.setString(THIRD_ELEMENT_IN_SQL_QUERY,
                    profile.getPassword());
            statement.setInt(FORTH_ELEMENT_IN_SQL_QUERY, profile.getOrders());
            statement.executeUpdate();

            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                LOGGER.error("There is no autoincremented index after trying"
                        + " to add record into table `users`");
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
     * Method reads object from database by id.
     *
     * @param id - id of object
     * @return object which was read
     * @throws PersistentException - exception with reading object from database
     */
    @Override
    public Profile read(final Integer id) throws PersistentException {
        String sql = "select login, password, orders from profiles"
                + " where id = (?)";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Profile profile = null;

            while (resultSet.next()) {
                profile = new Profile();
                profile.setLogin(resultSet.getString("login"));
                profile.setPassword(resultSet.getString("password"));
                profile.setOrders(resultSet.getInt("orders"));
            }

            return profile;
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
     * Method updates object in database by id.
     *
     * @param entity - updated object
     */
    @Override
    public void update(final Profile entity) throws PersistentException {
        String sql = "update profiles set login = ?, password = ?, orders = ?"
                + " where id = (?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setInt(THIRD_ELEMENT_IN_SQL_QUERY, entity.getOrders());
            statement.setInt(FORTH_ELEMENT_IN_SQL_QUERY, entity.getId());

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
     * Method deletes object in database by id.
     *
     * @param id - id of object for deletion
     */
    @Override
    public void delete(final Integer id) throws PersistentException {
        String sql = "delete from profiles where id = (?)";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

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
