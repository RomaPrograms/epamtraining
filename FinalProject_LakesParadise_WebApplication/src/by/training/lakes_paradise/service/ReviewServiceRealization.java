package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.dao.ReviewDao;
import by.training.lakes_paradise.db.entity.Review;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

public class ReviewServiceRealization extends ServiceRealization
        implements ReviewService {
    @Override
    public List<Review> readReviewsByHomeId(
            final Integer homeId) throws PersistentException {
        ReviewDao reviewDao = transaction.createDao(ReviewDao.class);
        return reviewDao.readByHomeId(homeId);
    }

    @Override
    public void deleteReviewsByHomeId(
            final Integer homeId) throws PersistentException {
        transaction.createDao(ReviewDao.class).deleteByHomeId(homeId);
    }

    @Override
    public Integer create(
            final Review review) throws PersistentException {
        ReviewDao reviewDao = transaction.createDao(ReviewDao.class);
        return reviewDao.create(review);
    }

    @Override
    public Review read(final Integer id) throws PersistentException {
        ReviewDao reviewDao = transaction.createDao(ReviewDao.class);
        return reviewDao.read(id);
    }

    @Override
    public void update(final Review review) throws PersistentException {
        transaction.createDao(ReviewDao.class).update(review);
    }

    @Override
    public void delete(final Integer id) throws PersistentException {
        transaction.createDao(ReviewDao.class).delete(id);
    }
}
