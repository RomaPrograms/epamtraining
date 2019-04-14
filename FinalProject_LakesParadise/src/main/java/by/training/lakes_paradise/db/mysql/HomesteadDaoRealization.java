package by.training.lakes_paradise.db.mysql;

import by.training.lakes_paradise.db.ConnectionDB;
import by.training.lakes_paradise.db.dao.HomesteadDao;
import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.entity.Owner;
import by.training.lakes_paradise.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 * Class for realization working with "homesteads" table.
 */
public class HomesteadDaoRealization implements HomesteadDao {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(HomesteadDaoRealization.class);

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
     * First part of query for getting data from table.
     */
    private static final String SQL_QUERY = "SELECT id, title, people_number,"
            + " price, description, rating, id_owner ";

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
     * Point to the seventh element in SQL query.
     */
    private static final int SEVENTH_ELEMENT_IN_SQL_QUERY = 7;

    /**
     * Method searches all homesteads in database by title.
     *
     * @param title - title
     * @return - list with objects that have expected title
     * @throws PersistentException - exception with searching in database
     */
    @Override
    public List<Homestead> findByTitle(final String title)
            throws PersistentException {
        String sql = SQL_QUERY + "FROM homesteads WHERE title = (?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);
            if (title != null) {
                statement.setString(1, title);
            }

            resultSet = statement.executeQuery();
            List<Homestead> homesteads = new ArrayList<>();

            while (resultSet.next()) {
                homesteads.add(createHomestead(resultSet));
            }

            return homesteads;
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION);
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error(CLOSE_RESULT_SET_EXCEPTION);
            }
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
     * Method searches all homesteads in database by price.
     *
     * @param minPrice - min price of homestead
     * @param maxPrice - max price of homestead
     * @return - list with objects that cost expected price
     * @throws PersistentException - exception with searching in database
     */
    @Override
    public List<Homestead> findByPrice(final BigDecimal minPrice,
                                       final BigDecimal maxPrice)
            throws PersistentException {
        String sql = SQL_QUERY
                + "FROM homesteads WHERE price >= (?) AND price <= (?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setBigDecimal(1, minPrice);
            statement.setBigDecimal(2, maxPrice);
            resultSet = statement.executeQuery();
            List<Homestead> homesteads = new ArrayList<>();

            while (resultSet.next()) {
                homesteads.add(createHomestead(resultSet));
            }

            return homesteads;
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION);
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error(CLOSE_RESULT_SET_EXCEPTION);
            }
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
     * Method adds new object to database.
     *
     * @param homestead - new object
     * @return - id of new object in database
     * @throws PersistentException - exception with adding object to database
     */
    @Override
    public Integer create(final Homestead homestead)
            throws PersistentException {
        String sql = "INSERT INTO homesteads (title, people_number, price,"
                + " description, rating, id_owner) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, homestead.getTitle());
            statement.setInt(2, homestead.getPeopleNumber());
            statement.setBigDecimal(
                    THIRD_ELEMENT_IN_SQL_QUERY, homestead.getPrice());
            statement.setString(
                    FORTH_ELEMENT_IN_SQL_QUERY, homestead.getDescription());
            statement.setDouble(
                    FIFTH_ELEMENT_IN_SQL_QUERY, homestead.getRating());
            statement.setInt(
                    SEVENTH_ELEMENT_IN_SQL_QUERY, homestead.getOwner().getId());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                LOGGER.error("There is no autoincremented index after"
                        + " trying to add record into table `homesteads`");
                throw new PersistentException();
            }

        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION);
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error(CLOSE_RESULT_SET_EXCEPTION);
            }
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
     * Method reads object from database by id.
     *
     * @param id - id of object
     * @return object which was read
     * @throws PersistentException - exception with reading object from database
     */
    @Override
    public Homestead read(final Integer id) throws PersistentException {
        String sql = "SELECT title, people_number, price, description, rating,"
                + " id_owner FROM homesteads WHERE id = (?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);

            if (id != null) {
                statement.setInt(1, id);
            }

            resultSet = statement.executeQuery();
            Homestead homestead = null;
            while (resultSet.next()) {
                homestead = new Homestead();
                homestead.setTitle(resultSet.getString("title"));
                homestead.setPeopleNumber(resultSet.getInt("status"));
                homestead.setPrice(resultSet.getBigDecimal("price"));
                homestead.setDescription(resultSet
                        .getString("description"));
                homestead.setRating(resultSet.getDouble("rating"));
                Owner owner = new Owner();
                owner.setId(resultSet.getInt("id_owner"));
                homestead.setOwner(owner);
            }
            return homestead;

        } catch (SQLException e) {
            LOGGER.error(CLOSE_RESULT_SET_EXCEPTION);
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                LOGGER.error(CLOSE_RESULT_SET_EXCEPTION);
            }
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
     * Method reads all object from homesteads table.
     *
     * @return - list with objects from homestead table
     * @throws PersistentException - exception with searching in database
     */
    @Override
    public List<Homestead> read() throws PersistentException {
        String sql = SQL_QUERY + "FROM homesteads";
        List<Homestead> list;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection;
        try {
            list = new ArrayList<>();
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                list.add(createHomestead(resultSet));
            }
            return list;
        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION);
            throw new PersistentException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(CLOSE_RESULT_SET_EXCEPTION);
            }
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException | NullPointerException e) {
                LOGGER.error(CLOSE_STATEMENT_EXCEPTION);
            }
        }
    }

    /**
     * Method updates object in database by id.
     *
     * @param entity - updated object
     */
    @Override
    public void update(final Homestead entity) {
        String sql = "UPDATE homesteads SET title = ?, people_number = ?,"
                + " price = ?, description = ?, rating = ?, id_owner = ?"
                + " where id = ?";
        PreparedStatement statement = null;
        Connection connection;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, entity.getTitle());
            statement.setInt(2, entity.getPeopleNumber());
            statement.setBigDecimal(
                    THIRD_ELEMENT_IN_SQL_QUERY, entity.getPrice());
            statement.setString(
                    FORTH_ELEMENT_IN_SQL_QUERY, entity.getDescription());
            statement.setDouble(
                    FIFTH_ELEMENT_IN_SQL_QUERY, entity.getRating());
            statement.setInt(
                    SIXTH_ELEMENT_IN_SQL_QUERY, entity.getOwner().getId());
            statement.setInt(
                    SEVENTH_ELEMENT_IN_SQL_QUERY, entity.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.error(SQL_EXCEPTION);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException | NullPointerException e) {
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
        String sql = "DELETE from homesteads where id = ?";
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
     * Method that creates object of homestead and initializes it.
     *
     * @param resultSet - resultSet for getting data from database
     * @return initializes homestead
     * @throws SQLException - Exception in working with database
     */
    private Homestead createHomestead(final ResultSet resultSet)
            throws SQLException {
        Homestead homestead = new Homestead();
        homestead.setId(resultSet.getInt("id"));
        homestead.setTitle(resultSet.getString("title"));
        homestead.setPeopleNumber(resultSet.getInt("peopleNumber"));
        homestead.setPrice(resultSet.getBigDecimal("price"));
        homestead.setDescription(resultSet
                .getString("description"));
        homestead.setRating(resultSet.getDouble("rating"));
        Owner owner = new Owner();
        owner.setId(resultSet.getInt("id_owner"));
        homestead.setOwner(owner);
        return homestead;
    }
}
