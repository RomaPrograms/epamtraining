package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.exception.PersistentException;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface with methods for working throw the DAO with "homesteads" table.
 */
public interface HomesteadService extends Service {
    /**
     * Method searches all homesteads in database by title.
     *
     * @param title - title
     * @return - list with objects that have expected title
     * @throws PersistentException - exception with searching in database
     */
    List<Homestead> readAllByTitle(String title) throws PersistentException;

    /**
     * Method searches all homesteads in database by price.
     *
     * @param minPrice - min price of homestead
     * @param maxPrice - max price of homestead
     * @return - list with objects that cost expected price
     * @throws PersistentException - exception with searching in database
     */
    List<Homestead> readAllByPrice(BigDecimal minPrice, BigDecimal maxPrice)
            throws PersistentException;

    /**
     * Method searches all homesteads in database by owner.
     *
     * @param ownerId - current id of owner
     * @return list with objects belong to current owner
     * @throws PersistentException - exception with searching in database
     */
    List<Homestead> readByOwner(int ownerId) throws PersistentException;

    /**
     * Method reads all object from homesteads table.
     *
     * @return - list with objects from homestead table
     * @throws PersistentException - exception with searching in database
     */
    List<Homestead> readAll() throws PersistentException;

    /**
     * Method adds new object to database.
     *
     * @param homestead - new object
     * @return - id of new object in database
     * @throws PersistentException - exception with adding object to database
     */
    Integer create(Homestead homestead) throws PersistentException;

    /**
     * Method reads object from homesteads table by id.
     *
     * @param id - id of object
     * @return object which was read
     * @throws PersistentException - exception with reading object from database
     */
    Homestead readById(Integer id) throws PersistentException;

    /**
     * Method updates homestead from homesteads table by id.
     *
     * @param entity - updated homestead
     * @throws PersistentException - exception with updating object from
     * database
     */
    void update(Homestead entity) throws PersistentException;

    /**
     * Method deletes homestead from homesteads table by id.
     *
     * @param id - id of homestead for deletion
     * @throws PersistentException - exception with deleting object from
     * database
     */
    void delete(Integer id) throws PersistentException;
}
