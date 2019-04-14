package by.training.lakes_paradise.db.dao;

import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

/**
 * Interface for working with "users" table.
 */
public interface UserDao extends Dao<User> {
    /**
     * Method that reads all objects from "users" table.
     *
     * @return list with objects from "users" table
     * @throws PersistentException - exception with searching in users table
     */
    List<User> read() throws PersistentException;
}
