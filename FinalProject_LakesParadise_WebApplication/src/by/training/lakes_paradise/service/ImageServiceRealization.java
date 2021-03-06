package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.dao.ImageDao;
import by.training.lakes_paradise.db.entity.Image;
import by.training.lakes_paradise.exception.PersistentException;

/**
 * Class with methods for working throw the DAO with "images" table.
 */
public class ImageServiceRealization extends ServiceRealization
        implements ImageService {
    @Override
    public Integer create(final Image image) throws PersistentException {
        ImageDao imageDao = transaction.createDao(ImageDao.class);
        return imageDao.create(image);
    }

    @Override
    public Image read(final Integer id) throws PersistentException {
        ImageDao imageDao = transaction.createDao(ImageDao.class);
        return imageDao.read(id);
    }
}
