package by.training.lakes_paradise.db.dao;

import by.training.lakes_paradise.db.entity.Entity;
import by.training.lakes_paradise.exception.PersistentException;

/**
 * Interface for realization DAO pattern.
 *
 * @param <Type> - some Entity class
 */
public interface Dao<Type extends Entity> {
    /**
     * Method adds new object to database.
     *
     * @param entity - new object
     * @return id of new object in database
     * @throws PersistentException - exception with adding object to database
     */
    Integer create(Type entity) throws PersistentException;

    /**
     * Method reads object from database by id.
     *
     * @param id - id of object
     * @return object which was read
     * @throws PersistentException - exception with reading object from database
     */
    Type read(Integer id) throws PersistentException;

    /**
     * Method updates object in database by id.
     *
     * @param entity - updated object
     * @throws PersistentException - exception with updating object in
     *                             database
     */
    void update(Type entity) throws PersistentException;

    /**
     * Method deletes object in database by id.
     *
     * @param id - id of object for deletion
     * @throws PersistentException - exception with deleting object from
     *                             database
     */
    void delete(Integer id) throws PersistentException;
}
