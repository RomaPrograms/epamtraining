package by.training.lakes_paradise.db.dao;

import by.training.lakes_paradise.db.entity.Image;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

/**
 * Interface for working with "image" table.
 */
public interface ImageDao extends Dao<Image> {
    /**
     * Method that finds all images by Id.
     *
     * @param id - id of image
     * @return list with images
     */
    List<Image> readImagesById(Integer id) throws PersistentException;

    /**
     *
     *
     * @param idHome
     * @throws PersistentException
     */
    void deleteImagesByIdHome(Integer idHome) throws PersistentException;
}
