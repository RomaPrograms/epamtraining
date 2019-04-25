package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.entity.Review;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

public interface ReviewService extends Service {
    List<Review> readReviewsByHomeId(Integer homeId) throws PersistentException;

    void deleteReviewsByHomeId(Integer homeId) throws PersistentException;
}
