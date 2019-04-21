package by.training.lakes_paradise.db.pool;

import by.training.lakes_paradise.db.mysql.OrderDaoRealization;
import by.training.lakes_paradise.exception.PersistentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPoolRealization implements ConnectionPool {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(ConnectionPoolRealization.class);

    private static final String CONNECTION_LIMIT_NUMBER_EXCEPTION
            = "The limit of number of database connections is exceeded";

    private static final String CONNECTION_RECEIVED_EXCEPTION
            = "It is impossible to connect to a database";

    private static final String CONNECTION_RECEIVED_MESSAGE
            = "Connection was received from pool. Current pool size: %d used"
            + " connections; %d free connection";

    private static final String CONNECTION_RETURNED_MESSAGE
            = "Connection was returned into pool. Current pool size: %d used"
            + " connections; %d free connection";

    private static final String CONNECTION_RETURNED_EXEPTION
            = "Connection was received from pool. Current pool size: %d used"
            + " connections; %d free connection";

    private static Lock lock = new ReentrantLock();

    private static int CHECK_CONNECTION_TIMEOUT;

    private static int MAX_POOL_SIZE;

    private static String URL;

    private static String USER;

    private static String PASSWORD;

    private static ConnectionPoolRealization INSTANCE
            = new ConnectionPoolRealization();

    private BlockingQueue<Connection> freeConnection
            = new LinkedBlockingQueue<>();
    private Set<Connection> usedConnection = new ConcurrentSkipListSet<>();

    @Override
    public ConnectionPoolRealization getInstance() {
        return INSTANCE;
    }

    @Override
    public void init(String driverClass, String url, String user,
                     String password, int startSize, int maxSize,
                     int checkConnectionTimeout) throws PersistentException {
        try {
            destroy();
            Class.forName(driverClass);
            this.URL = url;
            this.USER = user;
            this.PASSWORD = password;
            this.MAX_POOL_SIZE = maxSize;
            this.CHECK_CONNECTION_TIMEOUT = checkConnectionTimeout;
            for(int i = 0; i < startSize; i++) {
                freeConnection.put(createConnection());
            }
        } catch (ClassNotFoundException | SQLException
                | InterruptedException e) {
            LOGGER.fatal("It is imposible to initialize connection pool", e);
            throw new PersistentException(e);
        }
    }

    @Override
    public Connection getConnection() throws PersistentException {
        Connection connection = null;
        while (connection == null) {
            try {
                if (!freeConnection.isEmpty()) {
                    connection = freeConnection.take();
                    if (!connection.isValid(CHECK_CONNECTION_TIMEOUT)) { //возвращает true если соединение ещё не было закрыто и можно работать.
                        connection.close();
                        connection = null;
                    }
                } else {
                    if (usedConnection.size() < MAX_POOL_SIZE) {
                        usedConnection.add(createConnection());
                    } else {
                        LOGGER.error(CONNECTION_LIMIT_NUMBER_EXCEPTION);
                        throw new PersistentException();
                    }
                }
            } catch (InterruptedException | SQLException e) {
                LOGGER.error(CONNECTION_RECEIVED_EXCEPTION, e);
                lock.unlock();
                throw new PersistentException(e);
            }
        }
        usedConnection.add(connection);
        LOGGER.debug(String.format(CONNECTION_RECEIVED_MESSAGE,
                usedConnection.size(), freeConnection.size()));
        return connection;
    }

    @Override
    public void releaseConnection(Connection connection) {
        try {
            if (connection.isValid(CHECK_CONNECTION_TIMEOUT)) {
                connection.clearWarnings();
                connection.setAutoCommit(true);
                usedConnection.remove(connection);
                freeConnection.put(connection);
                LOGGER.debug(String.format(CONNECTION_RETURNED_MESSAGE,
                        usedConnection.size(), freeConnection.size()));
            }
        } catch (SQLException | InterruptedException e) {
            LOGGER.warn(CONNECTION_RECEIVED_EXCEPTION);
            try {
                connection.close();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public void destroy() {
        usedConnection.addAll(freeConnection);
        freeConnection.clear();
        for (Connection connection : usedConnection) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        usedConnection.clear();
    }
}
