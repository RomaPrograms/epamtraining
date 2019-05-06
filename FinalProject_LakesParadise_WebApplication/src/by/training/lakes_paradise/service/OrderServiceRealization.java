package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.dao.OrderDao;
import by.training.lakes_paradise.db.entity.Order;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

public class OrderServiceRealization extends ServiceRealization
        implements OrderService {
    @Override
    public List<Order> readByProfile(Integer profileId) throws PersistentException {
        return null;
    }

    @Override
    public List<Order> readByHomestead(Integer homesteadId) throws PersistentException {
        OrderDao orderDaoRealization = transaction.createDao(OrderDao.class);
        return orderDaoRealization.readByHomestead(homesteadId);
    }

    @Override
    public List<Order> read() throws PersistentException {
        return null;
    }

    @Override
    public Integer create(Order order) throws PersistentException {
        OrderDao orderDaoRealization = transaction.createDao(OrderDao.class);
        return orderDaoRealization.create(order);
    }

    @Override
    public Order read(Integer id) throws PersistentException {
        return null;
    }

    @Override
    public void update(Order order) throws PersistentException {

    }

    @Override
    public void delete(Integer id) throws PersistentException {

    }
}
