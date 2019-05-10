package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.dao.HomesteadDao;
import by.training.lakes_paradise.db.dao.ImageDao;
import by.training.lakes_paradise.db.dao.ReviewDao;
import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.exception.PersistentException;

import java.math.BigDecimal;
import java.util.List;

/**
 * Class with methods for working throw the DAO with "homesteads" table.
 */
public class HomesteadServiceRealization extends ServiceRealization
        implements HomesteadService {

    /**
     * Method searches all homesteads in database by owner.
     *
     * @param ownerId - current id of owner
     * @return list with objects belong to current owner
     * @throws PersistentException - exception with searching in database
     */
    @Override
    public List<Homestead> readByOwner(final int ownerId)
            throws PersistentException {
        HomesteadDao homesteadDao = transaction.createDao(HomesteadDao.class);
        return homesteadDao.readByOwner(ownerId);
    }

    /**
     * Method searches all homesteads in database by title.
     *
     * @param title - title
     * @return - list with objects that have expected title
     * @throws PersistentException - exception with searching in database
     */
    @Override
    public List<Homestead> readAllByTitle(
            final String title) throws PersistentException {
        HomesteadDao homesteadDao = transaction.createDao(HomesteadDao.class);
        return homesteadDao.readByTitle(title);
    }

    /**
     * Method searches all homesteads in database by price.
     *
     * @param minPrice - min price of homestead
     * @param maxPrice - max price of homestead
     * @return - list with objects that cost expected price
     * @throws PersistentException - exception with searching in database
     */
    @Override
    public List<Homestead> readAllByPrice(
            final BigDecimal minPrice,
            final BigDecimal maxPrice) throws PersistentException {
        HomesteadDao homesteadDao = transaction.createDao(HomesteadDao.class);
        return homesteadDao.readByPrice(minPrice, maxPrice);
    }

    /**
     * Method reads all object from homesteads table.
     *
     * @return - list with objects from homestead table
     * @throws PersistentException - exception with searching in database
     */
    @Override
    public List<Homestead> readAll() throws PersistentException {
        HomesteadDao homesteadDao = transaction.createDao(HomesteadDao.class);
        return homesteadDao.read();
    }

    /**
     * Method adds new object to database.
     *
     * @param homestead - new object
     * @return - id of new object in database
     * @throws PersistentException - exception with adding object to database
     */
    @Override
    public Integer create(final Homestead homestead)
            throws PersistentException {
        HomesteadDao homesteadDao = transaction.createDao(HomesteadDao.class);
        return homesteadDao.create(homestead);
    }

    /**
     * Method reads object from database by id.
     *
     * @param id - id of object
     * @return object which was read
     * @throws PersistentException - exception with reading object from database
     */
    @Override
    public Homestead readById(final Integer id) throws PersistentException {
        HomesteadDao homesteadDao = transaction.createDao(HomesteadDao.class);
        ImageDao imageDao = transaction.createDao(ImageDao.class);
        ReviewDao reviewDao = transaction.createDao(ReviewDao.class);
        Homestead homestead = homesteadDao.read(id);
        for (var image : imageDao.readByHomeId(id)) {
            homestead.getImages().add(image);
        }
        for (var review : reviewDao.readByHomeId(id)) {
            homestead.getReviews().add(review);
        }
        return homestead;
    }

    /**
     * Method updates homestead from homesteads table by id.
     *
     * @param homestead - updated homestead
     */
    @Override
    public void update(final Homestead homestead) throws PersistentException {
        HomesteadDao homesteadDao = transaction.createDao(HomesteadDao.class);
        homesteadDao.update(homestead);
    }

    /**
     * Method deletes homestead from homesteads table by id.
     *
     * @param id - id of homestead for deletion
     */
    @Override
    public void delete(final Integer id) throws PersistentException {
        HomesteadDao homesteadDao = transaction.createDao(HomesteadDao.class);
        homesteadDao.delete(id);
    }
}
