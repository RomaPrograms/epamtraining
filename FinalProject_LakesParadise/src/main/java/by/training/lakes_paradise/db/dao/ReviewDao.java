package by.training.lakes_paradise.db.dao;

import by.training.lakes_paradise.db.entity.Image;
import by.training.lakes_paradise.db.entity.Review;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

public interface ReviewDao extends Dao<Review> {
    /**
     * Method that finds all reviews by id of homestead.
     *
     * @param homeId - id of homestead
     * @return list with images
     */
    List<Review> readReviewsByHomeId(Integer homeId) throws PersistentException;

    /**
     * @param homeId
     * @throws PersistentException
     */
    void deleteReviewsByHomeId(Integer homeId) throws PersistentException;
}
