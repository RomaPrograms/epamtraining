package by.training.lakes_paradise.db.mysql;

import by.training.lakes_paradise.db.dao.Transaction;
import by.training.lakes_paradise.db.dao.HomesteadDao;
import by.training.lakes_paradise.db.dao.ImageDao;
import by.training.lakes_paradise.db.dao.ReviewDao;
import by.training.lakes_paradise.db.dao.ProfileDao;
import by.training.lakes_paradise.db.dao.OrderDao;
import by.training.lakes_paradise.db.dao.UserDao;
import by.training.lakes_paradise.db.dao.Dao;
import by.training.lakes_paradise.exception.PersistentException;

import java.lang.reflect.InvocationTargetException;
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

    public TransactionRealization(final Connection connection) {
        this.connection = connection;
    }

    @Override
    public <Type extends Dao<?>> Type createDao(
            final Class<Type> key) throws PersistentException {
        Class<? extends BaseDaoRealization> value = classes.get(key);
        if (value != null) {
            try {
                BaseDaoRealization dao;
                dao = value.getConstructor().newInstance();
                dao.setConnection(connection);
                return (Type) dao;
            } catch (InstantiationException | IllegalAccessException e) {
                LOGGER.error(
                        "It is impossible to create data access object", e);
                throw new PersistentException(e);
            } catch (InvocationTargetException e) {
                LOGGER.error("Constructor of " + value.getSimpleName()
                        + " throws an exception", e);
                throw new PersistentException(e);
            } catch (NoSuchMethodException e) {
                LOGGER.error("Matching method is not found", e);
                throw new PersistentException(e);
            }
        }
        throw new PersistentException("Dao class named " + key.getSimpleName()
                + " wasn't founded");
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
