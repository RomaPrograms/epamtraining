package by.training.lakes_paradise.db.pool;

import by.training.lakes_paradise.exception.PersistentException;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionPool {
    Connection getConnection()
            throws InterruptedException, SQLException, PersistentException;
    void releaseConnection(PooledConnection connection);
    void init(String driverClass, String url, String user, String password,
              int startSize, int maxSize) throws PersistentException;
    PooledConnection createConnection() throws SQLException;
    void destroy();
}
