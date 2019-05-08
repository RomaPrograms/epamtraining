package by.training.lakes_paradise.db.mysql;

import by.training.lakes_paradise.db.dao.OrderDao;
import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.entity.Order;
import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for realization working with "orders" table.
 */
public class OrderDaoRealization extends BaseDaoRealization
        implements OrderDao {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(OrderDaoRealization.class);

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
     * String first part of SQL query for reading from database.
     */
    private static final String SQL_SCRIPT_SELECT
            = "select o.user_id, o.home_id, o.date_start, o.date_end, "
            + "o.status_pay, h.title, p.login from orders o inner join homesteads h on"
            + " o.home_id = h.id inner join profiles p on o.user_id = p.id ";

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
     * Point to the sixth element in SQL query.
     */
    private static final int SIXTH_ELEMENT_IN_SQL_QUERY = 6;

    /**
     * Script gets all objects from table orders.
     */
    private static final String SQL_SCRIPT_SELECT_DATA_FROM_TABLE
            = SQL_SCRIPT_SELECT + " orders";

    /**
     * Script insert new object into the table orders.
     */
    private static final String SQL_SCRIPT_INSERT_DATA_INTO_TABLE
            = "insert into orders (user_id, home_id, date_start,"
            + " date_end, status_pay) values (?, ?, ?, ?, ?)";

    /**
     * Script gets all objects from table orders by id.
     */
    private static final String SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_ID
            = "select user_id, home_id, date_start, date_end, "
            + "status_pay from orders where id = (?)";

    /**
     * Script gets all objects from table orders by profile id.
     */
    private static final String SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_PROFILE_ID
            = SQL_SCRIPT_SELECT + "where o.user_id = ?";

    /**
     * Script gets all objects from table orders by home id.
     */
    private static final String SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_HOME_ID
            = SQL_SCRIPT_SELECT + "where o.home_id = ?";

    /**
     * Script updates object in table orders.
     */
    private static final String SQL_SCRIPT_UPDATE_DATA_IN_TABLE
            = "update orders set user_id = ?, home_id = ?,"
            + " date_start = ?, date_end = ?, status_pay = ?"
            + " where id = ?";

    /**
     * Method that search all orders by id of profile.
     *
     * @param profileId - id of profile
     * @return list with orders which were done by expected profile
     * @throws PersistentException - exception with searching in orders table
     */
    @Override
    public List<Order> readByProfile(final Integer profileId)
            throws PersistentException {
        return readByCategory(
                SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_PROFILE_ID, profileId);
    }

    /**
     * Method that search all orders by id of homestead.
     *
     * @param homesteadId - id of homestead
     * @return list with orders which were done with expected homestead
     * @throws PersistentException - exception with searching in orders table
     */
    @Override
    public List<Order> readByHomestead(final Integer homesteadId)
            throws PersistentException {
        return readByCategory(
                SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_HOME_ID, homesteadId);
    }

    /**
     * Method that reads all objects from "orders" table.
     *
     * @return list with objects from "orders" table
     * @throws PersistentException - exception with searching in orders table
     */
    @Override
    public List<Order> read() throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SCRIPT_SELECT_DATA_FROM_TABLE,
                    Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();

            while (resultSet.next()) {
                orders.add(createOrder(resultSet));
            }

            return orders;
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
     * @param order - new object
     * @return - id of new object in database
     * @throws PersistentException - exception with adding object to database
     */
    @Override
    public Integer create(final Order order) throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SCRIPT_INSERT_DATA_INTO_TABLE,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(
                    1, order.getUser().getId());
            statement.setInt(
                    2, order.getHomestead().getId());
            statement.setDate(THIRD_ELEMENT_IN_SQL_QUERY,
                    new Date(order.getStartRenting().getTime()));
            statement.setDate(FORTH_ELEMENT_IN_SQL_QUERY,
                    new Date(order.getEndRenting().getTime()));
            statement.setBoolean(FIFTH_ELEMENT_IN_SQL_QUERY,
                    order.getPaid());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                LOGGER.error("There is no autoincremented index after trying"
                        + " to create record into table `orders`");
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
    public Order read(final Integer id) throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_ID,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Order order = null;

            while (resultSet.next()) {
                order = new Order();
                User user = new User();
                user.setId(
                        resultSet.getInt("user_id"));
                order.setUser(user);
                Homestead homestead = new Homestead();
                homestead.setId(
                        resultSet.getInt("home_id"));
                order.setHomestead(homestead);
                Date date = resultSet.getDate("date_start");
                order.setStartRenting(new java.util.Date(date.getTime()));
                date = resultSet.getDate("date_end");
                order.setEndRenting(new java.util.Date(date.getTime()));
                order.setPaid(
                        resultSet.getBoolean("status_pay"));
            }

            return order;
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
     * @param order - updated object
     */
    @Override
    public void update(final Order order) throws PersistentException {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SCRIPT_UPDATE_DATA_IN_TABLE);
            statement.setInt(
                    1, order.getUser().getId());
            statement.setInt(
                    2, order.getHomestead().getId());
            statement.setDate(THIRD_ELEMENT_IN_SQL_QUERY,
                    new Date(order.getStartRenting().getTime()));
            statement.setDate(FORTH_ELEMENT_IN_SQL_QUERY,
                    new Date(order.getEndRenting().getTime()));
            statement.setBoolean(FIFTH_ELEMENT_IN_SQL_QUERY,
                    order.getPaid());
            statement.setInt(SIXTH_ELEMENT_IN_SQL_QUERY,
                    order.getId());

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
            statement = delete("orders", id);
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
     * Method that search all orders by some category.
     *
     * @param sql - sql script which will be executed.
     * @param id  - id of parameter
     * @return list with orders
     * @throws PersistentException - exception with reading objects from
     *                             database
     */
    private List<Order> readByCategory(final String sql, final Integer id)
            throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();

            while (resultSet.next()) {
                orders.add(createOrder(resultSet));
            }

            return orders;
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
     * Method that creates object of order and initializes it.
     *
     * @param resultSet - resultSet with data from database
     * @return initialized order
     * @throws SQLException - Exception in working with database
     */
    private Order createOrder(final ResultSet resultSet) throws SQLException {
        Order order = new Order();
        User user = new User();
        user.setId(resultSet.getInt("user_id"));
        user.setLogin(resultSet.getString("login"));
        order.setUser(user);
        Homestead homestead = new Homestead();
        homestead.setId(
                resultSet.getInt("home_id"));
        homestead.setTitle(resultSet.getString("title"));
        order.setHomestead(homestead);
        Date date = resultSet.getDate("date_start");
        order.setStartRenting(new java.util.Date(date.getTime()));
        date = resultSet.getDate("date_end");
        order.setEndRenting(new java.util.Date(date.getTime()));
        order.setPaid(resultSet
                .getBoolean("status_pay"));
        return order;
    }
}
