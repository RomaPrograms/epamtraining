package by.training.lakes_paradise.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.sql.Connection;

public class ConnectionDB {
    public static Connection getConnection() throws SQLException {
        String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3306/lakes_paradise_db?useUnicode=true&characterEncoding=UTF-8";
        String DB_USER = "root";
        String DB_PASSWORD = "9512684Roma";

        try {
            Class.forName(DB_DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }
}
