package by.training.lakes_paradise.db.dao;

import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.exception.PersistentException;

/**
 * Interface for working with "profiles" table.
 */
public interface ProfileDao extends Dao<Profile> {
    /**
     * Method searches profile of user by login and password.
     *
     * @param login    - login of user
     * @param password - profile of user
     * @return profile of user
     * @throws PersistentException - exception with searching in profile table
     */
    Profile read(String login, String password) throws PersistentException;
}
