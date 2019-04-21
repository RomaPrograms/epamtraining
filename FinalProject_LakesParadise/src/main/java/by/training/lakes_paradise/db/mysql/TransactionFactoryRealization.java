package by.training.lakes_paradise.db.mysql;

import by.training.lakes_paradise.db.dao.Transaction;
import by.training.lakes_paradise.db.dao.TransactionFactory;
import by.training.lakes_paradise.db.pool.ConnectionPool;
import by.training.lakes_paradise.exception.PersistentException;
import java.sql.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class TransactionFactoryRealization implements TransactionFactory {
    private static Logger LOGGER
            = LogManager.getLogger(TransactionFactoryRealization.class);
    private Connection connection;

    public TransactionFactoryRealization() throws PersistentException {
        connection = ConnectionPool.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            LOGGER.error("It is impossible to turn off autocommiting for database connection", e);
            throw new PersistentException(e);
        }
    }

    @Override
    public Transaction createTransaction() throws PersistentException {
        return new TransactionRealization(connection);
    }

    @Override
    public void close() {
        try{
            this.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
