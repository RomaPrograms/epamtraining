package by.training.lakes_paradise.db;

import java.util.ResourceBundle;
import java.sql.*;

public class ConnectionDB {
    public static Connection getConnection() throws SQLException {
        ResourceBundle resource = ResourceBundle.getBundle("property.database");
        String url = resource.getString("db.url");
        String user = resource.getString("db.user");
        String pass = resource.getString("db.password");
        try {
            Class.forName(resource.getString("db.driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(url, user, pass);
    }
}
