package by.training.lakes_paradise.db.mysql;

import by.training.lakes_paradise.db.dao.UserDao;
import by.training.lakes_paradise.db.entity.Role;
import by.training.lakes_paradise.db.entity.User;
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
 * Class for realization working with "users" table.
 */
public class UserDaoRealization extends BaseDaoRealization implements UserDao {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(UserDaoRealization.class);

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
     * Script gets all objects from table users.
     */
    private static final String SQL_SCRIPT_SELECT_DATA_FROM_TABLE
            = "select u.id, u.name, u.surname, u.phone, p.login, p.role from"
            + " users u inner join profiles p on u.id = p.id";

    /**
     * Script insert new object into the table users.
     */
    private static final String SQL_SCRIPT_INSERT_DATA_INTO_TABLE
            = "insert into users (id, name, surname, phone) values"
            + " (?, ?, ?, ?)";

    /**
     * Script gets all objects from table users by id.
     */
    private static final String SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_ID
            = "select name, surname, phone from users where"
            + " id = (?)";


    /**
     * Script updates object in table users.
     */
    private static final String SQL_SCRIPT_UPDATE_DATA_IN_TABLE
            = "update users set name = ?, surname = ?, phone = ?"
            + " where id = (?)";

    /**
     * Script returns all users by in some part of login.
     */
    private static final String SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_LOGIN
            = SQL_SCRIPT_SELECT_DATA_FROM_TABLE + " where p.login like ?";

    /**
     * Method that reads all objects from "users" table.
     *
     * @return list with objects from "users" table
     */
    @Override
    public List<User> read() throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SCRIPT_SELECT_DATA_FROM_TABLE);
            resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            User user;

            while (resultSet.next()) {
                user = readUserWithProfileProperties(resultSet);
                users.add(user);
            }

            return users;
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
     * Method adds new user to "users" table.
     *
     * @param user - new object
     * @return - id of new object in database
     * @throws PersistentException - exception with adding object to database
     */
    @Override
    public Integer create(final User user) throws PersistentException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SCRIPT_INSERT_DATA_INTO_TABLE,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, user.getId());
            statement.setString(2, user.getName());
            statement.setString(THIRD_ELEMENT_IN_SQL_QUERY, user.getSurname());
            statement.setLong(FORTH_ELEMENT_IN_SQL_QUERY, user.getPhone());
            return statement.executeUpdate();

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
     * Finds users by login.
     *
     * @param login - login of user.
     * @return list with users
     * @throws PersistentException - exception connected with DAO.
     */
    @Override
    public List<User> readByLogin(final String login)
            throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_LOGIN);
            statement.setString(1, '%' + login + '%');
            resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            User user;

            while (resultSet.next()) {
                user = readUserWithProfileProperties(resultSet);
                users.add(user);
            }

            return users;
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
     * Method reads user from "users" table by id.
     *
     * @param id - id of object
     * @return object which was read
     * @throws PersistentException - exception with reading object from database
     */
    @Override
    public User read(final Integer id) throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_ID);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            User user = null;

            while (resultSet.next()) {
                user = new User();
                user.setName(resultSet.getString("name"));
                user.setSurname(resultSet.getString("surname"));
                user.setPhone(Long.parseLong(resultSet
                        .getString("phone")));
            }

            return user;
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
     * Method updates user from "users" table by id.
     *
     * @param user - updated object
     */
    @Override
    public void update(final User user) throws PersistentException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SCRIPT_UPDATE_DATA_IN_TABLE);

            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setLong(THIRD_ELEMENT_IN_SQL_QUERY, user.getPhone());
            statement.setInt(FORTH_ELEMENT_IN_SQL_QUERY, user.getId());
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
     * Method deletes user from "users" table by id.
     *
     * @param id - id of object for deletion
     */
    @Override
    public void delete(final Integer id) throws PersistentException {
        PreparedStatement statement = null;

        try {
            statement = delete("users", id);
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
     * Creates instance {@code User} class with filled properties.
     *
     * @param resultSet - result set with data from database
     * @return created user
     * @throws SQLException - exception connected with database
     */
    private User readUserWithProfileProperties(final ResultSet resultSet)
            throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setPhone(Long.parseLong(resultSet
                .getString("phone")));
        user.setLogin(resultSet.getString("login"));
        user.setRole(Role.getByIdentity(
                resultSet.getInt("role")));
        return user;
    }
}
