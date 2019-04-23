package by.training.lakes_paradise.db.mysql;

import by.training.lakes_paradise.db.dao.*;
import by.training.lakes_paradise.exception.PersistentException;
import java.sql.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class TransactionRealization implements Transaction {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(TransactionRealization.class);
    private Connection connection;

    private static Map<Class<? extends Dao<?>>, Class<? extends
            BaseDaoRealization>> classes = new ConcurrentHashMap<>();

    static {
        classes.put(HomesteadDao.class, HomesteadDaoRealization.class);
        classes.put(ImageDao.class, ImageDaoRealization.class);
        classes.put(OrderDao.class, OrderDaoRealization.class);
        classes.put(ProfileDao.class, ProfileDaoRealization.class);
        classes.put(UserDao.class, UserDaoRealization.class);
        classes.put(ReviewDao.class, ReviewDaoRealization.class);
    }

    public TransactionRealization(Connection connection) {
        this.connection = connection;
    }

    @Override
    public <Type extends Dao<?>> Type createDao(Class<Type> key) throws PersistentException {
        Class<? extends BaseDaoRealization> value = classes.get(key);
        if (value != null) {
            try {
                BaseDaoRealization dao = null;
                dao = value.newInstance();

                /*System.out.println(dao);
                dao = value.cast(dao);
                System.out.println(dao);*/

                dao.setConnection(connection);
                return (Type) dao;
            } catch (InstantiationException | IllegalAccessException e) {
                LOGGER.error("It is impossible to create data access object", e);
                throw new PersistentException(e);
            }
        }
        return null;
    }

    @Override
    public void commit() throws PersistentException {
        try {
            connection.commit();
        } catch (SQLException e) {
            LOGGER.error("It is impossible to commit transaction", e);
            throw new PersistentException(e);
        }
    }

    @Override
    public void rollback() throws PersistentException {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.error("It is impossible to rollback transaction", e);
            throw new PersistentException(e);
        }
    }
}
