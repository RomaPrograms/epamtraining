package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.dao.OrderDao;
import by.training.lakes_paradise.db.entity.Order;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

public class OrderServiceRealization extends ServiceRealization
        implements OrderService {
    @Override
    public List<Order> readByProfile(final Integer profileId)
            throws PersistentException {
        OrderDao orderDaoRealization = transaction.createDao(OrderDao.class);
        return orderDaoRealization.readByProfile(profileId);
    }

    @Override
    public List<Order> readByHomestead(final Integer homesteadId)
            throws PersistentException {
        OrderDao orderDaoRealization = transaction.createDao(OrderDao.class);
        return orderDaoRealization.readByHomestead(homesteadId);
    }

    @Override
    public List<Order> readAll() throws PersistentException {
        return null;
    }

    @Override
    public Integer create(final Order order) throws PersistentException {
        OrderDao orderDaoRealization = transaction.createDao(OrderDao.class);
        return orderDaoRealization.create(order);
    }

    @Override
    public Order read(final Integer id) throws PersistentException {
        return null;
    }

    @Override
    public void update(final Order order) throws PersistentException {

    }

    @Override
    public void delete(final Integer id) throws PersistentException {

    }
}
