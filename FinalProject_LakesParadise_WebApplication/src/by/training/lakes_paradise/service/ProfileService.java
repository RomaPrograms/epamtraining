package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

public interface ProfileService extends Service {
    /**
     * Method returns profile of user by login and password.
     *
     * @param login    - login of user
     * @param password - profile of user
     * @return profile of user
     */
    Profile read(String login, String password) throws PersistentException;

    /**
     * Method reads all objects from "profiles" table.
     *
     * @return objects from "profiles" table
     */
    List<Profile> readAll() throws PersistentException;

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
     */
    void update(Profile profile) throws PersistentException;

    /**
     * Method deletes object in "profiles" table by id.
     *
     * @param id - id of object for deletion
     */
    void delete(Integer id) throws PersistentException;
}
