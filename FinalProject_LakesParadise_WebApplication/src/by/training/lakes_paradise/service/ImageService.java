package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.entity.Image;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

/**
 * Interface with methods for working throw the DAO with "images" table.
 */
public interface ImageService extends Service {
    Integer create(Image image) throws PersistentException;

    List<Image> readByHomeId(Integer homeId) throws PersistentException;

    Image read(Integer id) throws PersistentException;

}
