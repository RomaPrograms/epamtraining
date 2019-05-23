package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.entity.Review;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

/**
 * Interface with methods for working throw the DAO with "reviews" table.
 */
public interface ReviewService extends Service {
    /**
     * Method that searches all reviews by id of homestead in "orders" table.
     *
     * @param homeId - id of homestead
     * @return list with images
     * @throws PersistentException - exception with searching in review table by
     * homestead id
     */
    List<Review> readReviewsByHomeId(Integer homeId) throws PersistentException;

    /**
     * Method adds new review to "reviews" table.
     *
     * @param review - new object
     * @return - id of new object in database
     * @throws PersistentException - exception with adding object to database
     */
    Integer create(Review review) throws PersistentException;

    /**
     * Method reads object to "reviews" table.
     *
     * @param id - id of object
     * @return object which was read
     * @throws PersistentException - exception with reading object from database
     */
    Review read(Integer id) throws PersistentException;

    /**
     * Method that updates from "reviews" table.
     *
     * @param review - updated reviews
     * @throws PersistentException - exception with updating in review table
     */
    void update(Review review) throws PersistentException;

    /**
     * Method deletes review from "reviews" table by review id.
     *
     * @param id - id of object for deletion
     * @throws PersistentException - exception with deleting from review table

     */
    void delete(Integer id) throws PersistentException;
}
