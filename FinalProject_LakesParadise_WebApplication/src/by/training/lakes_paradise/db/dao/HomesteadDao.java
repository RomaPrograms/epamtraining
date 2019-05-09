package by.training.lakes_paradise.db.dao;

import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.exception.PersistentException;

import java.math.BigDecimal;
import java.util.List;

/**
 * Interface for working with "homesteads" table.
 */
public interface HomesteadDao extends Dao<Homestead> {
    /**
     * Method searches all homesteads in database by title.
     *
     * @param search - title
     * @return - list with objects that have expected title
     * @throws PersistentException - exception with searching in database
     */
    List<Homestead> readByTitle(String search) throws PersistentException;

    /**
     * Method searches all homesteads in database by price.
     *
     * @param minPrice - min price of homestead
     * @param maxPrice - max price of homestead
     * @return - list with objects that cost expected price
     * @throws PersistentException - exception with searching in database
     */
    List<Homestead> readByPrice(BigDecimal minPrice, BigDecimal maxPrice)
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
     * Method reads all objects from homesteads table.
     *
     * @return - list with objects from homestead table
     * @throws PersistentException - exception with searching in database
     */
    List<Homestead> read() throws PersistentException;
}
