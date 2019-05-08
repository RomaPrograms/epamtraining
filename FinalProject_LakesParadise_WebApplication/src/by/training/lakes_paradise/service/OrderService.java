package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.entity.Order;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

public interface OrderService extends Service {
    List<Order> readByProfile(Integer profileId)
            throws PersistentException;

    List<Order> readByHomestead(Integer homesteadId)
            throws PersistentException;

    List<Order> readAll() throws PersistentException;

    Integer create(Order order) throws PersistentException;

    Order read(Integer id) throws PersistentException;

    void update(Order order) throws PersistentException;

    void delete(Integer id) throws PersistentException;
}
