package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.dao.HomesteadDao;
import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.exception.PersistentException;

import java.math.BigDecimal;
import java.util.List;

public class HomesteadServiceRealization extends ServiceRealization
        implements HomesteadService{
    @Override
    public List<Homestead> findAllByTitle(String search) throws PersistentException {
        HomesteadDao homesteadDao = transaction.createDao(HomesteadDao.class);
        return homesteadDao.findByTitle(search);
    }

    @Override
    public List<Homestead> findAllByPrice(BigDecimal minPrice, BigDecimal maxPrice) throws PersistentException {
        HomesteadDao homesteadDao = transaction.createDao(HomesteadDao.class);
        return homesteadDao.findByPrice(minPrice, maxPrice);
    }

    @Override
    public List<Homestead> findAll() throws PersistentException {
        HomesteadDao homesteadDao = transaction.createDao(HomesteadDao.class);
        return homesteadDao.read();
    }

    @Override
    public Integer add(Homestead homestead) throws PersistentException {
        HomesteadDao homesteadDao = transaction.createDao(HomesteadDao.class);
        return homesteadDao.create(homestead);
    }

    @Override
    public Homestead findById(Integer id) throws PersistentException {
        HomesteadDao homesteadDao = transaction.createDao(HomesteadDao.class);
        return homesteadDao.read(id);
    }

    @Override
    public void update(Homestead homestead) throws PersistentException {
        HomesteadDao homesteadDao = transaction.createDao(HomesteadDao.class);
        homesteadDao.update(homestead);
    }

    @Override
    public void delete(Integer id) throws PersistentException {
        HomesteadDao homesteadDao = transaction.createDao(HomesteadDao.class);
        homesteadDao.delete(id);
    }
}
