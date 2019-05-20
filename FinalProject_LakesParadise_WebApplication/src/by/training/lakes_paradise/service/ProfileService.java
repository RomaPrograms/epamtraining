package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.exception.PersistentException;

/**
 * Interface with methods for working throw the DAO with "profiles" table.
 */
public interface ProfileService extends Service {
    /**
     * Method returns profile of user by login and password.
     *
     * @param login    - login of user
     * @param password - profile of user
     * @return profile of user
     * @throws PersistentException - exception with reading object from database
     */
    Profile read(String login, String password) throws PersistentException;

    /**
     * Method adds new object to "profiles" table.
     *
     * @param profile - new object
     * @return - id of new object in database
     * @throws PersistentException - exception with adding object to database
     */
    Integer create(Profile profile) throws PersistentException;

    /**
     * Method reads object from "profiles" table by id.
     *
     * @param id - id of object
     * @return object which was read
     * @throws PersistentException - exception with reading object from database
     */
    Profile read(Integer id) throws PersistentException;

    /**
     * Method updates object in "profiles" table by id.
     *
     * @param profile - updated object
     * @throws PersistentException - exception with reading object from database
     */
    void update(Profile profile) throws PersistentException;

    /**
     * Method deletes object in "profiles" table by id.
     *
     * @param id - id of object for deletion
     * @throws PersistentException - exception with reading object from database
     */
    void delete(Integer id) throws PersistentException;
}
