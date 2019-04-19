package by.training.lakes_paradise.db.dao;

import by.training.lakes_paradise.db.entity.Image;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

/**
 * Interface for working with "image" table.
 */
public interface ImageDao extends Dao<Image> {
    /**
     * Method that searches all images by id of homestead.
     *
     * @param homeId - id of homestead
     * @return list with images
     * @throws PersistentException - exception with searching in database
     */
    List<Image> readImagesByHomeId(Integer homeId) throws PersistentException;

    /**
     * Methods that deletes images by id of homestead.
     *
     * @param homeId - id of homestead
     * @throws PersistentException - exception with deleting in database
     */
    void deleteImagesByHomeId(Integer homeId) throws PersistentException;
}
