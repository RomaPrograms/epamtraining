package by.training.lakes_paradise.db.dao;

import by.training.lakes_paradise.db.entity.Image;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

/**
 * Interface for working with "image" table.
 */
public interface ImageDao extends Dao<Image> {
    /**
     * Method that finds all images by id of homestead.
     *
     * @param homeId - id of homestead
     * @return list with images
     */
    List<Image> readImagesByHomeId(Integer homeId) throws PersistentException;

    /**
     * @param homeId
     * @throws PersistentException
     */
    void deleteImagesByHomeId(Integer homeId) throws PersistentException;
}