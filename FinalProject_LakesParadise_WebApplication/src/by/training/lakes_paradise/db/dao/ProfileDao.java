package by.training.lakes_paradise.db.dao;

import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

/**
 * Interface for working with "profiles" table.
 */
public interface ProfileDao extends Dao<Profile> {
    /**
     * Method reads all objects from "profiles" table.
     *
     * @return objects from "profiles" table
     * @throws PersistentException - exception with searching in profile table
     */
    List<Profile> read() throws PersistentException;

    /**
     * Method searches profile of user by login and password.
     *
     * @param login    - login of user
     * @param password - profile of user
     * @return profile of user
     * @throws PersistentException - exception with searching in profile table
     */
    Profile read(String login, String password) throws PersistentException;


    List<Profile> readByLogin(String login) throws PersistentException;
}
