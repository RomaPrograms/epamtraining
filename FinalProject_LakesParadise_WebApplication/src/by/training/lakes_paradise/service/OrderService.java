package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.entity.Order;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

/**
 * Interface with methods for working throw the DAO with "orders" table.
 */
public interface OrderService extends Service {
    /**
     * Method that search all orders by id of profile.
     *
     * @param profileId - id of profile
     * @return list with orders which were done by expected profile
     * @throws PersistentException - exception with searching in orders table
     */
    List<Order> readByProfile(Integer profileId)
            throws PersistentException;

    /**
     * Method that search all orders by id of homestead.
     *
     * @param homesteadId - id of homestead
     * @return list with orders which were done with expected homestead
     * @throws PersistentException - exception with searching in orders table
     */
    List<Order> readByHomestead(Integer homesteadId)
            throws PersistentException;

    /**
     * Method that search all orders by id of owner.
     *
     * @param ownerId - id of homestead
     * @return list with orders which were done with expected homestead
     * @throws PersistentException - exception with searching in orders table
     */
    List<Order> readByOwner(Integer ownerId) throws PersistentException;

    /**
     * Method adds new object to "orders" table.
     *
     * @param order - new object
     * @return - id of new object in database
     * @throws PersistentException - exception with adding object to database
     */
    Integer create(Order order) throws PersistentException;

    /**
     * Method reads object from "orders" table by id.
     *
     * @param id - id of object
     * @return object which was read
     * @throws PersistentException - exception with reading object from database
     */
    Order read(Integer id) throws PersistentException;

    /**
     * Method updates object in "orders" table by id.
     *
     * @param order - updated object
     * @throws PersistentException - exception with reading object from database
     */
    void update(Order order) throws PersistentException;

    /**
     * Method deletes object in "orders" table by id.
     *
     * @param id - id of object for deletion
     * @throws PersistentException - exception with reading object from database
     */
    void delete(Integer id) throws PersistentException;
}
