package by.training.lakes_paradise.db.dao;

import by.training.lakes_paradise.db.entity.Review;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

/**
 * Interface for working with "reviews" table.
 */
public interface ReviewDao extends Dao<Review> {
    /**
     * Method that searches all reviews by id of homestead.
     *
     * @param homeId - id of homestead
     * @return list with images
     * @throws PersistentException - exception with searching in review table by
     *                             homestead id
     */
    List<Review> readReviewsByHomeId(Integer homeId) throws PersistentException;

    /**
     * Method deletes reviews by id of homestead.
     *
     * @param homeId - id of homestead
     * @throws PersistentException - exception with deleting in review table
     */
    void deleteReviewsByHomeId(Integer homeId) throws PersistentException;
}
