package by.training.lakes_paradise.db.mysql;

import by.training.lakes_paradise.db.ConnectionDB;
import by.training.lakes_paradise.db.dao.OrderDao;
import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.entity.Order;
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
 * Class for realization working with "orders" table.
 */
public class OrderDaoRealization implements OrderDao {

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
    private static final String SQL_QUERY
            = "select id, id_profile, id_home, date_start, date_end, "
            + "status_pay from";

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
     * Method that search all orders by id of profile.
     *
     * @param profileId - id of profile
     * @return list with orders which were done by expected profile
     * @throws PersistentException - exception with searching in orders table
     */
    @Override
    public List<Order> readByProfile(final Integer profileId)
            throws PersistentException {
        String sql = SQL_QUERY + " orders where id_profile = ?";
        return readByCategory(sql, profileId);
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
        String sql = SQL_QUERY + " orders where id_home = ?";
        return readByCategory(sql, homesteadId);
    }

    /**
     * Method that reads all objects from "orders" table.
     *
     * @return list with objects from "orders" table
     * @throws PersistentException - exception with searching in orders table
     */
    @Override
    public List<Order> read() throws PersistentException {
        String sql = SQL_QUERY + " orders";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql,
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
        String sql = "insert into orders (id_profile, id_home, date_start,"
                + " date_end, status_pay)"
                + " values (?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, order.getProfile().getId());
            statement.setInt(2, order.getHomestead().getId());
            statement.setDate(THIRD_ELEMENT_IN_SQL_QUERY,
                    new Date(order.getStartRenting()));
            statement.setDate(FORTH_ELEMENT_IN_SQL_QUERY,
                    new Date(order.getEndRenting()));
            statement.setBoolean(FIFTH_ELEMENT_IN_SQL_QUERY,
                    order.getPaid());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                LOGGER.error("There is no autoincremented index after trying"
                        + " to add record into table `books`");
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
        String sql = "select id_profile, id_home, date_start, date_end, "
                + "status_pay from orders where id = (?)";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Order order = null;

            while (resultSet.next()) {
                order = new Order();
                Profile profile = new Profile();
                profile.setId(resultSet.getInt("id_profile"));
                order.setProfile(profile);
                Homestead homestead = new Homestead();
                homestead.setId(resultSet.getInt("id_home"));
                order.setHomestead(homestead);
                order.setStartRenting(resultSet
                        .getDate("date_start").getTime());
                order.setEndRenting(resultSet
                        .getDate("date_end").getTime());
                order.setPaid(resultSet.getBoolean("status_pay"));
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
     * @param entity - updated object
     */
    @Override
    public void update(final Order entity) {
        String sql = "update orders set id_profile = ?, id_home = ?,"
                + " date_start = ?, date_end = ?, status_pay = ?,"
                + " where id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getProfile().getId());
            statement.setInt(2, entity.getHomestead().getId());
            statement.setDate(THIRD_ELEMENT_IN_SQL_QUERY,
                    new Date(entity.getStartRenting()));
            statement.setDate(FORTH_ELEMENT_IN_SQL_QUERY,
                    new Date(entity.getEndRenting()));
            statement.setBoolean(FIFTH_ELEMENT_IN_SQL_QUERY,
                    entity.getPaid());
            statement.setInt(SIXTH_ELEMENT_IN_SQL_QUERY,
                    entity.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION);
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
    public void delete(final Integer id) {
        String sql = "delete from orders where id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION);
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
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql,
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
        order.setId(resultSet.getInt("id"));
        Profile profile = new Profile();
        profile.setId(resultSet.getInt("id_profile"));
        order.setProfile(profile);
        Homestead homestead = new Homestead();
        homestead.setId(resultSet.getInt("id_home"));
        order.setHomestead(homestead);
        order.setStartRenting(resultSet
                .getDate("date_start").getTime());
        order.setEndRenting(resultSet
                .getDate("date_end").getTime());
        order.setPaid(resultSet.getBoolean("status_pay"));
        return order;
    }
}
