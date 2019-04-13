package by.training.lakes_paradise.db.mysql;

import by.training.lakes_paradise.db.ConnectionDB;
import by.training.lakes_paradise.db.dao.OrderDao;
import by.training.lakes_paradise.entity.Homestead;
import by.training.lakes_paradise.entity.Order;
import by.training.lakes_paradise.entity.Profile;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoRealization implements OrderDao {

    private static final Logger LOGGER
            = LogManager.getLogger(HomesteadDaoRealization.class);

    @Override
    public List<Order> readByProfile(Integer profileId) {
        String sql = "select id, id_profile, id_home, date_start, date_end, "
                + "status_pay, number_people from orders where id_profile = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, profileId);
            resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            Order order;

            while(resultSet.next()) {
                order = new Order();
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
                order.setNumberOfPeople(resultSet
                        .getInt("number_people"));

                orders.add(order);
            }

            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Order> readByHomestead(Integer homesteadId) {
        String sql = "select id, id_profile, id_home, date_start, date_end, "
                + "status_pay, number_people from orders where id_home = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, homesteadId);
            resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            Order order;

            while(resultSet.next()) {
                order = new Order();
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
                order.setNumberOfPeople(resultSet
                        .getInt("number_people"));

                orders.add(order);
            }

            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Order> read() {
        String sql = "select id, id_profile, id_home, date_start, date_end, "
                + "status_pay, number_people from orders";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            resultSet = statement.executeQuery();
            List<Order> orders = new ArrayList<>();
            Order order;

            while(resultSet.next()) {
                order = new Order();
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
                order.setNumberOfPeople(resultSet
                        .getInt("number_people"));

                orders.add(order);
            }

            return orders;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Integer create(Order entity) {
        String sql = "insert into orders (id_profile, id_home, date_start,"
                + " date_end, status_pay, number_people)"
                + " values (?, ?, ?, ?, ?, ?)";
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getProfile().getId());
            statement.setInt(2, entity.getHomestead().getId());
            statement.setDate(3,
                    new Date(entity.getStartRenting()));
            statement.setDate(4, new Date(entity.getEndRenting()));
            statement.setBoolean(5, entity.getPaid());
            statement.setInt(6, entity.getNumberOfPeople());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                LOGGER.error("There is no autoincremented index after trying to add record into table `books`");
                //here I should throw an exception.
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Order read(Integer id) {
        String sql = "select id_profile, id_home, date_start, date_end, "
                + "status_pay, number_people from orders where id = (?)";
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

            while(resultSet.next()) {
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
                order.setNumberOfPeople(resultSet
                        .getInt("number_people"));
            }

            return order;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void update(Order entity) {
        String sql = "update orders set id_profile = ?, id_home = ?,"
                + " date_start = ?, date_end = ?, status_pay = ?,"
                + " number_people = ? where id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getProfile().getId());
            statement.setInt(2, entity.getHomestead().getId());
            statement.setDate(3,
                    new Date(entity.getStartRenting()));
            statement.setDate(4, new Date(entity.getEndRenting()));
            statement.setBoolean(5, entity.getPaid());
            statement.setInt(6, entity.getNumberOfPeople());
            statement.setInt(7, entity.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "delete from orders where id = ?";
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
