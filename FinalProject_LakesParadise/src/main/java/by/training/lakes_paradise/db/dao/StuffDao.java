package by.training.lakes_paradise.db.dao;

import by.training.lakes_paradise.db.entity.Stuff;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

/**
 * Interface for working with "stuff" table.
 */
public interface StuffDao extends Dao<Stuff>{
    /**
     * Method which searches stuff by login and password.
     *
     * @param login - login of stuff
     * @param password - password of stuff
     * @return searched stuff
     * @throws PersistentException - exception with searching in stuff table
     */
    Stuff read(String login, String password) throws PersistentException;

    /**
     * Method which searches all stuff in stuff table.
     *
     * @return list with stuff
     * @throws PersistentException - exception with reading in stuff table
     */
    List<Stuff> read() throws PersistentException;
}
