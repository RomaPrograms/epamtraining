package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

/**
 * Interface with methods for working throw the DAO with "users" table.
 */
public interface UserService extends Service {
    /**
     * Method that reads all objects from "users" table.
     *
     * @return list with objects from "users" table
     * @throws PersistentException - exception with reading object from database
     */
    List<User> readAll() throws PersistentException;

    /**
     * Method adds new user to "users" table.
     *
     * @param user - new object
     * @return - id of new object in database
     * @throws PersistentException - exception with adding object to database
     */
    Integer create(User user) throws PersistentException;

    /**
     * Method reads user from "users" table by id.
     *
     * @param id - id of object
     * @return object which was read
     * @throws PersistentException - exception with reading object from database
     */
    User read(Integer id) throws PersistentException;

    /**
     * Method reads user from "users" table by login.
     *
     * @param login - login of users
     * @return list with users which was read by login
     * @throws PersistentException - exception with reading object from database
     */
    List<User> readByLogin(String login) throws PersistentException;

    /**
     * Method updates user from "users" table by id.
     *
     * @param user - updated object
     * @throws PersistentException - exception with updating object from
     * database
     */
    void update(User user) throws PersistentException;

    /**
     * Method deletes user from "users" table by id.
     *
     * @param id - id of object for deletion
     * @throws PersistentException - exception with deleting object from
     * database
     */
    void delete(Integer id) throws PersistentException;
}
