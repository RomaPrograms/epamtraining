package by.training.lakesParadise.db.mysql;

import by.training.lakesParadise.db.ConnectionDB;
import by.training.lakesParadise.db.dao.HomesteadDao;
import by.training.lakesParadise.entity.Homestead;
import by.training.lakesParadise.entity.Owner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class HomesteadDaoRealization implements HomesteadDao {

    private static final Logger LOGGER
            = LogManager.getLogger(HomesteadDaoRealization.class);

    @Override
    public List<Homestead> findByTitle(String title) {
        String sql = "SELECT title, status, price, description, rating,"
                + " id_owner FROM homestead WHERE title = (?)";
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
            Homestead homestead;

            while(resultSet.next()) {
                homestead = new Homestead();
                homestead.setTitle(resultSet.getString("title"));
                homestead.setStatus(resultSet.getBoolean("status"));
                homestead.setPrice(resultSet.getBigDecimal("price"));
                homestead.setDescription(resultSet
                        .getString("description"));
                homestead.setRating(resultSet.getDouble("rating"));
                Integer idOwner = resultSet.getInt("id_owner");
                if (!resultSet.wasNull()) {
                    Owner owner = new Owner();
                    owner.setId(idOwner);
                    homestead.setOwner(owner);
                }
                homesteads.add(homestead);
            }

            return homesteads;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @Override
    public List<Homestead> findByPrice(BigDecimal minPrice, BigDecimal maxPrice) {
        String sql = "SELECT title, status, price, description, rating,"
                + " id_owner FROM homestead WHERE price >= (?) AND price <= (?)";
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
            Homestead homestead;

            while(resultSet.next()) {
                homestead = new Homestead();
                homestead.setTitle(resultSet.getString("title"));
                homestead.setStatus(resultSet.getBoolean("status"));
                homestead.setPrice(resultSet.getBigDecimal("price"));
                homestead.setDescription(resultSet
                        .getString("description"));
                homestead.setRating(resultSet.getDouble("rating"));
                Integer idOwner = resultSet.getInt("id_owner");
                if (!resultSet.wasNull()) {
                    Owner owner = new Owner();
                    owner.setId(idOwner);
                    homestead.setOwner(owner);
                }
                homesteads.add(homestead);
            }

            return homesteads;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Integer create(Homestead homestead) {
        String sql = "INSERT INTO homestead (title, status, price, description,"
                + " rating, id_owner) values (?, ?, ?,"
                + " ?, ?, ?)";
        PreparedStatement statement = null;
        //ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1,homestead.getTitle());
            statement.setBoolean(2, homestead.getStatus());
            statement.setBigDecimal(3, homestead.getPrice());
            statement.setString(4, homestead.getDescription());
            statement.setDouble(5, homestead.getRating());
            statement.setInt(6, homestead.getOwner().getId());

            statement.executeUpdate();
            /*resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                LOGGER.error("There is no autoincremented index after trying to add record into table `books`");
                //here I should throw an exception.
            }*/

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            /*try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }*/
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Homestead read(Integer id) {
        String sql = "SELECT title, status, price, description, rating, id_owner FROM homestead WHERE id = (?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);

            if(id != null) {
                statement.setInt(1, id);
            }

            resultSet = statement.executeQuery();
            Homestead homestead = null;
            while(resultSet.next()){
                homestead = new Homestead();
                homestead.setTitle(resultSet.getString("title"));
                homestead.setStatus(resultSet.getBoolean("status"));
                homestead.setPrice(resultSet.getBigDecimal("price"));
                homestead.setDescription(resultSet
                        .getString("description"));
                homestead.setRating(resultSet.getDouble("rating"));
                Integer owner_id = resultSet.getInt("id_owner");
                Owner owner = new Owner();
                owner.setId(owner_id);
                homestead.setOwner(owner);
            }
            return homestead;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public List<Homestead> read() {
        String sql = "SELECT title, status, price, description, rating,"
                + " id_owner FROM homestead";
        List<Homestead> list;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Connection connection;
        try {
            list = new ArrayList<>();
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);

            resultSet = statement.executeQuery();
            Homestead homestead;
            while(resultSet.next()){
                homestead = new Homestead();
                homestead.setTitle(resultSet.getString("title"));
                homestead.setStatus(resultSet.getBoolean("status"));
                homestead.setPrice(resultSet.getBigDecimal("price"));
                homestead.setDescription(resultSet
                        .getString("description"));
                homestead.setRating(resultSet.getDouble("rating"));
                Integer owner_id = resultSet.getInt("id_owner");
                Owner owner = new Owner();
                owner.setId(owner_id);
                homestead.setOwner(owner);
                list.add(homestead);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void update(Homestead entity) {
        String sql = "UPDATE homestead SET title = ?, status = ?, price = ?,"
                + " description = ?, rating = ?, id_owner = ? where id = ?";
        PreparedStatement statement = null;
        Connection connection;
        try {
            connection = ConnectionDB.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, entity.getTitle());
            statement.setBoolean(2, entity.getStatus());
            statement.setBigDecimal(3, entity.getPrice());
            statement.setString(4, entity.getDescription());
            statement.setDouble(5, entity.getRating());
            statement.setInt(6, entity.getOwner().getId());
            statement.setInt(7, entity.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void delete(Integer id) {
        String sql = "DELETE from homestead where id = ?";
        PreparedStatement statement = null;
        Connection connection;
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
            } catch (SQLException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}
