package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.dao.ReviewDao;
import by.training.lakes_paradise.db.entity.Review;
import by.training.lakes_paradise.db.mysql.ReviewDaoRealization;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

public class ReviewServiceRealization extends ServiceRealization
        implements ReviewService{
    @Override
    public List<Review> readReviewsByHomeId(Integer homeId) throws PersistentException {
        ReviewDao reviewDao = transaction.createDao(ReviewDao.class);
        return reviewDao.readReviewsByHomeId(homeId);
    }

    @Override
    public void deleteReviewsByHomeId(Integer homeId) throws PersistentException {
        transaction.createDao(ReviewDao.class).deleteReviewsByHomeId(homeId);
    }

    @Override
    public Integer create(Review review) throws PersistentException {
        ReviewDao reviewDao = transaction.createDao(ReviewDao.class);
        return reviewDao.create(review);
    }

    @Override
    public Review read(Integer id) throws PersistentException {
        ReviewDao reviewDao = transaction.createDao(ReviewDao.class);
        return reviewDao.read(id);
    }

    @Override
    public void update(Review review) throws PersistentException {
        transaction.createDao(ReviewDao.class).update(review);
    }

    @Override
    public void delete(Integer id) throws PersistentException {
        transaction.createDao(ReviewDao.class).delete(id);
    }
}
