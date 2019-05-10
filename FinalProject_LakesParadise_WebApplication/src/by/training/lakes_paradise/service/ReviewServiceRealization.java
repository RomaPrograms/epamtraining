package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.dao.ReviewDao;
import by.training.lakes_paradise.db.entity.Review;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

/**
 * Class with methods for working throw the DAO with "reviews" table.
 */
public class ReviewServiceRealization extends ServiceRealization
        implements ReviewService {
    /**
     * Method that searches all reviews by id of homestead in "orders" table.
     *
     * @param homeId - id of homestead
     * @return list with images
     * @throws PersistentException - exception with searching in review table by
     * homestead id
     */
    @Override
    public List<Review> readReviewsByHomeId(
            final Integer homeId) throws PersistentException {
        ReviewDao reviewDao = transaction.createDao(ReviewDao.class);
        return reviewDao.readByHomeId(homeId);
    }

    @Override
    public void deleteReviewsByHomeId(
            final Integer homeId) throws PersistentException {
        ReviewDao reviewDao = transaction.createDao(ReviewDao.class);
        reviewDao.deleteByHomeId(homeId);
    }

    /**
     * Method adds new review to "reviews" table.
     *
     * @param review - new object
     * @return - id of new object in database
     * @throws PersistentException - exception with adding object to database
     */
    @Override
    public Integer create(
            final Review review) throws PersistentException {
        ReviewDao reviewDao = transaction.createDao(ReviewDao.class);
        return reviewDao.create(review);
    }

    /**
     * Method reads object to "reviews" table.
     *
     * @param id - id of object
     * @return object which was read
     * @throws PersistentException - exception with reading object from database
     */
    @Override
    public Review read(final Integer id) throws PersistentException {
        ReviewDao reviewDao = transaction.createDao(ReviewDao.class);
        return reviewDao.read(id);
    }

    /**
     * Method that updates from "reviews" table.
     *
     * @param review - updated reviews
     * @throws PersistentException - exception with updating in review table
     */
    @Override
    public void update(final Review review) throws PersistentException {
        ReviewDao reviewDao = transaction.createDao(ReviewDao.class);
        reviewDao.update(review);
    }

    /**
     * Method deletes review from "reviews" table by review id.
     *
     * @param id - id of object for deletion
     */
    @Override
    public void delete(final Integer id) throws PersistentException {
        ReviewDao reviewDao = transaction.createDao(ReviewDao.class);
        reviewDao.delete(id);
    }
}
