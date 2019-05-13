package by.training.lakes_paradise.db.dao;

import by.training.lakes_paradise.db.entity.Order;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

/**
 * Interface for working with "orders" table.
 */
public interface OrderDao extends Dao<Order> {
    /**
     * Method that searches all orders by id of profile.
     *
     * @param profileId - id of profile
     * @return list with orders which were done by expected profile
     * @throws PersistentException - exception with searching in orders table
     */
    List<Order> readByProfile(Integer profileId) throws PersistentException;

    /**
     * Method that searches all orders by id of homestead.
     *
     * @param homesteadId - id of homestead
     * @return list with orders which were done with expected homestead
     * @throws PersistentException - exception with searching in orders table
     */
    List<Order> readByHomestead(Integer homesteadId) throws PersistentException;

    /**
     * Method that searches all orders by owner id.
     *
     * @param ownerId - id of owner
     * @return list with orders which were done with expected homestead
     * @throws PersistentException - exception with searching in orders table
     */
    List<Order> readByOwner(Integer ownerId) throws PersistentException;

    /**
     * Method that reads all objects from "orders" table.
     *
     * @return list with objects from "orders" table
     * @throws PersistentException - exception with searching in orders table
     */
    List<Order> read() throws PersistentException;
}
