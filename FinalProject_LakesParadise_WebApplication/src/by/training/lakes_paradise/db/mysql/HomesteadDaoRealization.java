package by.training.lakes_paradise.db.mysql;

import by.training.lakes_paradise.db.dao.HomesteadDao;
import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for realization working with "homesteads" table.
 */
public class HomesteadDaoRealization extends BaseDaoRealization
        implements HomesteadDao {

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
    private static final String SQL_SCRIPT_SELECT_DATA_FROM_TABLE
            = "SELECT * from homesteads h inner join users u on"
            + " h.owner_id = u.id ";

    /**
     * Script gets all objects from table homesteads by title.
     */
    private static final String SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_TITLE
            = SQL_SCRIPT_SELECT_DATA_FROM_TABLE + " where h.title like `%`?`%`";

    /**
     * Script gets all objects from table homesteads by price.
     */
    private static final String SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_PRICE
            = SQL_SCRIPT_SELECT_DATA_FROM_TABLE
            + " WHERE h.price BETWEEN (?) AND (?)";

    /**
     * Script insert new object into the table homesteads.
     */
    private static final String SQL_SCRIPT_INSERT_DATA_INTO_TABLE
            = "INSERT INTO homesteads (title, price, description,"
            + " people_number, rating, number_of_voted_users, owner_id)"
            + " values (?, ?, ?, ?, ?, ?, ?)";

    /**
     * Script gets all objects from table homesteads by id.
     */
    private static final String SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_ID
            = SQL_SCRIPT_SELECT_DATA_FROM_TABLE + "WHERE h.id = (?)";

    /**
     * Script gets all objects from table homesteads.
     */
    private static final String SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_OWNER_ID
            = SQL_SCRIPT_SELECT_DATA_FROM_TABLE + " where h.owner_id=(?)";

    /**
     * Script updates object in table homesteads.
     */
    private static final String SQL_SCRIPT_UPDATE_DATA_IN_TABLE
            = "UPDATE homesteads SET title = ?, people_number = ?,"
            + " price = ?, description = ?, rating = ?,"
            + " number_of_voted_users = ?, owner_id = ? where id = ?";

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
     * Point to the seventh element in SQL query.
     */
    private static final int EIGHTH_ELEMENT_IN_SQL_QUERY = 8;

    /**
     * Method searches all homesteads in database by title.
     *
     * @param title - title
     * @return - list with objects that have expected title
     * @throws PersistentException - exception with searching in database
     */
    @Override
    public List<Homestead> readByTitle(final String title)
            throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_TITLE);
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
     * Method searches all homesteads in database by owner.
     *
     * @param ownerId - current id of owner
     * @return list with objects belong to current owner
     * @throws PersistentException - exception with searching in database
     */
    @Override
    public List<Homestead> readByOwner(final int ownerId)
            throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = getConnection().prepareStatement(
                    SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_OWNER_ID);

            statement.setInt(1, ownerId);
            resultSet = statement.executeQuery();
            List<Homestead> homesteads = new ArrayList<>();
            Homestead homestead;
            while (resultSet.next()) {
                homestead = createHomestead(resultSet);
                homesteads.add(homestead);
            }

            return homesteads;

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
     * Method searches all homesteads in database by price.
     *
     * @param minPrice - min price of homestead
     * @param maxPrice - max price of homestead
     * @return - list with objects that cost expected price
     * @throws PersistentException - exception with searching in database
     */
    @Override
    public List<Homestead> readByPrice(final BigDecimal minPrice,
                                       final BigDecimal maxPrice)
            throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_PRICE);

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
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SCRIPT_INSERT_DATA_INTO_TABLE,
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(
                    1, homestead.getTitle());
            statement.setBigDecimal(
                    2, homestead.getPrice());
            statement.setString(
                    THIRD_ELEMENT_IN_SQL_QUERY, homestead.getDescription());
            statement.setInt(
                    FORTH_ELEMENT_IN_SQL_QUERY, homestead.getPeopleNumber());
            statement.setDouble(
                    FIFTH_ELEMENT_IN_SQL_QUERY, homestead.getRating());
            statement.setLong(
                    SIXTH_ELEMENT_IN_SQL_QUERY,
                    homestead.getNumberOfVotedUsers());
            statement.setInt(
                    SEVENTH_ELEMENT_IN_SQL_QUERY, homestead.getOwner().getId());

            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                LOGGER.error("There is no autoincremented index after"
                        + " trying to create record into table `homesteads`");
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
     * Method reads all objects from homesteads table by id.
     *
     * @param id - id of object
     * @return object which was read
     * @throws PersistentException - exception with reading object from database
     */
    @Override
    public Homestead read(final Integer id) throws PersistentException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            statement = getConnection().prepareStatement(
                    SQL_SCRIPT_SELECT_DATA_FROM_TABLE_BY_ID);

            if (id != null) {
                statement.setInt(1, id);
            }

            resultSet = statement.executeQuery();
            Homestead homestead = null;
            while (resultSet.next()) {
                homestead = createHomestead(resultSet);
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
        List<Homestead> list;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            list = new ArrayList<>();
            statement = getConnection().prepareStatement(
                    SQL_SCRIPT_SELECT_DATA_FROM_TABLE);

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
     * Method updates homestead from homesteads table by id.
     *
     * @param entity - updated homestead
     */
    @Override
    public void update(final Homestead entity) {
        PreparedStatement statement = null;
        try {
            statement = getConnection().prepareStatement(
                    SQL_SCRIPT_UPDATE_DATA_IN_TABLE);

            statement.setString(
                    1, entity.getTitle());
            statement.setInt(
                    2, entity.getPeopleNumber());
            statement.setBigDecimal(
                    THIRD_ELEMENT_IN_SQL_QUERY, entity.getPrice());
            statement.setString(
                    FORTH_ELEMENT_IN_SQL_QUERY, entity.getDescription());
            statement.setDouble(
                    FIFTH_ELEMENT_IN_SQL_QUERY, entity.getRating());
            statement.setLong(
                    SIXTH_ELEMENT_IN_SQL_QUERY, entity.getNumberOfVotedUsers());
            statement.setInt(
                    SEVENTH_ELEMENT_IN_SQL_QUERY, entity.getOwner().getId());
            statement.setInt(
                    EIGHTH_ELEMENT_IN_SQL_QUERY, entity.getId());
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
     * Method deletes homestead from homesteads table by id.
     *
     * @param id - id of homestead for deletion
     */
    @Override
    public void delete(final Integer id) throws PersistentException {
        PreparedStatement statement = null;

        try {
            statement = delete("homesteads", id);
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
     * Method that creates object of homestead and initializes it.
     *
     * @param resultSet - resultSet for getting data from database
     * @return initializes homestead
     * @throws SQLException - Exception in working with database
     */
    private Homestead createHomestead(final ResultSet resultSet)
            throws SQLException {
        Homestead homestead = new Homestead();
        homestead.setId(
                resultSet.getInt("id"));
        homestead.setTitle(
                resultSet.getString("title"));
        homestead.setPeopleNumber(
                resultSet.getInt("people_number"));
        homestead.setPrice(
                resultSet.getBigDecimal("price"));
        homestead.setDescription(
                resultSet.getString("description"));
        homestead.setRating(
                resultSet.getDouble("rating"));
        homestead.setNumberOfVotedUsers(
                resultSet.getLong("number_of_voted_users"));

        User owner = new User();
        owner.setId(
                resultSet.getInt("owner_id"));
        owner.setName(resultSet.getString("name"));
        owner.setSurname(resultSet.getString("surname"));
        owner.setPhone(resultSet.getLong("phone"));

        homestead.setOwner(owner);
        return homestead;
    }
}
