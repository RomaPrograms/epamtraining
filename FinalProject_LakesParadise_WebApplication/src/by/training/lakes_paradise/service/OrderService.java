package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.entity.Order;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

public interface OrderService extends Service {
    List<Order> readByProfile(final Integer profileId)
            throws PersistentException;

    List<Order> readByHomestead(final Integer homesteadId)
            throws PersistentException;

    List<Order> read() throws PersistentException;

    Integer create(final Order order) throws PersistentException;

    Order read(final Integer id) throws PersistentException;

    void update(final Order order) throws PersistentException;

    void delete(final Integer id) throws PersistentException;
}
