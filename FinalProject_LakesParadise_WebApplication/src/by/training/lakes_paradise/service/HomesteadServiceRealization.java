package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.dao.HomesteadDao;
import by.training.lakes_paradise.db.dao.ImageDao;
import by.training.lakes_paradise.db.dao.ReviewDao;
import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.exception.PersistentException;

import java.math.BigDecimal;
import java.util.List;

public class HomesteadServiceRealization extends ServiceRealization
        implements HomesteadService {

    @Override
    public List<Homestead> readByOwner(int ownerId)
            throws PersistentException {
        HomesteadDao homesteadDao = transaction.createDao(HomesteadDao.class);
        return homesteadDao.readByOwner(ownerId);
    }

    @Override
    public List<Homestead> readAllByTitle(
            final String search) throws PersistentException {
        HomesteadDao homesteadDao = transaction.createDao(HomesteadDao.class);
        return homesteadDao.readByTitle(search);
    }

    @Override
    public List<Homestead> readAllByPrice(
            final BigDecimal minPrice,
            final BigDecimal maxPrice) throws PersistentException {
        HomesteadDao homesteadDao = transaction.createDao(HomesteadDao.class);
        return homesteadDao.readByPrice(minPrice, maxPrice);
    }

    @Override
    public List<Homestead> readAll() throws PersistentException {
        HomesteadDao homesteadDao = transaction.createDao(HomesteadDao.class);
        return homesteadDao.read();
    }

    @Override
    public Integer create(final Homestead homestead)
            throws PersistentException {
        HomesteadDao homesteadDao = transaction.createDao(HomesteadDao.class);
        return homesteadDao.create(homestead);
    }

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

    @Override
    public void update(final Homestead homestead) throws PersistentException {
        HomesteadDao homesteadDao = transaction.createDao(HomesteadDao.class);
        homesteadDao.update(homestead);
    }

    @Override
    public void delete(final Integer id) throws PersistentException {
        HomesteadDao homesteadDao = transaction.createDao(HomesteadDao.class);
        homesteadDao.delete(id);
    }
}
