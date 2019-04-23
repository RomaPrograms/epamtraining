package by.training.lakes_paradise.db.mysql;

import by.training.lakes_paradise.db.ConnectionDB;
import by.training.lakes_paradise.db.dao.ProfileDao;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.entity.Role;
import by.training.lakes_paradise.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for realization working with "profiles" table.
 */
public class ProfileDaoRealization extends BaseDaoRealization
        implements ProfileDao {

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
     * Script gets all objects from table profiles.
     */
    private static final String SQL_SCRIPT_SELECT_DATA_FROM_TABLE
            = "select id, login, password, role from profiles";

    /**
     * Script insert new object into the table profiles.
     */
    private static final String SQL_SCRIPT_INSERT_DATA_INTO_TABLE
            = "insert into profiles (id, login, password, role) values"
            + " (?, ?, ?, ?)";

    /**
     * Script gets all objects from table profiles by id.
     */
    private static final String SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_ID
            = "select login, password, role from profiles"
            + " where id = (?)";

    /**
     * Script gets all objects from table profiles by login and password.
     */
    private static final String
            SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_LOGIN_AND_PASSWORD
            = "select id, login, password, role from profiles"
            + " where login = (?) and password = (?)";

    /**
     * Script updates object in table profiles.
     */
    private static final String SQL_SCRIPT_UPDATE_DATA_IN_TABLE
            = "update profiles set login = ?, password = ?, role = ?"
            + " where id = (?)";

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
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(
                    SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            Profile profile = null;

            while (resultSet.next()) {
                profile = new Profile();
                profile.setId(resultSet.getInt("id"));
                profile.setLogin(resultSet.getString("login"));
                profile.setPassword(resultSet.getString("password"));
                profile.setRole(Role.getByIdentity(
                        resultSet.getInt("role")));
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
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(
                    SQL_SCRIPT_SELECT_DATA_FROM_TABLE);
            resultSet = statement.executeQuery();
            List<Profile> profiles = new ArrayList<>();
            Profile profile = null;

            while (resultSet.next()) {
                profile = new Profile();
                profile.setId(resultSet.getInt("id"));
                profile.setLogin(resultSet.getString("login"));
                profile.setPassword(resultSet.getString("password"));
                profile.setRole(Role.getByIdentity(
                        resultSet.getInt("role")));

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
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(
                    SQL_SCRIPT_INSERT_DATA_INTO_TABLE,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, profile.getId());
            statement.setString(2, profile.getLogin());
            statement.setString(THIRD_ELEMENT_IN_SQL_QUERY,
                    profile.getPassword());
            statement.setInt(FORTH_ELEMENT_IN_SQL_QUERY,
                    profile.getRole().getIdentity());
            statement.executeUpdate();

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
     * Method reads object from database by id.
     *
     * @param id - id of object
     * @return object which was read
     * @throws PersistentException - exception with reading object from database
     */
    @Override
    public Profile read(final Integer id) throws PersistentException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(
                    SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Profile profile = null;

            while (resultSet.next()) {
                profile = new Profile();
                profile.setLogin(resultSet.getString("login"));
                profile.setPassword(resultSet.getString("password"));
                profile.setRole(Role.getByIdentity(
                        resultSet.getInt("role")));
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
     * @param profile - updated object
     */
    @Override
    public void update(final Profile profile) throws PersistentException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(
                    SQL_SCRIPT_UPDATE_DATA_IN_TABLE);
            statement.setString(1, profile.getLogin());
            statement.setString(2, profile.getPassword());
            statement.setInt(THIRD_ELEMENT_IN_SQL_QUERY,
                    profile.getRole().getIdentity());
            statement.setInt(FORTH_ELEMENT_IN_SQL_QUERY, profile.getId());

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
        PreparedStatement statement = null;

        try {
            statement = delete("profiles", id);
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
