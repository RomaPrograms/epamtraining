package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.exception.PersistentException;

import java.math.BigDecimal;
import java.util.List;

public class HomesteadServiceRealization implements HomesteadService{
    @Override
    public List<Homestead> findAllByTitle(String search) throws PersistentException {
        return null;
    }

    @Override
    public List<Homestead> findAllByPrice(BigDecimal minPrice, BigDecimal maxPrice) throws PersistentException {
        return null;
    }

    @Override
    public List<Homestead> findAll() throws PersistentException {
        return null;
    }

    @Override
    public Integer add(Homestead homestead) throws PersistentException {
        return null;
    }

    @Override
    public Homestead findById(Integer id) throws PersistentException {
        return null;
    }

    @Override
    public void update(Homestead entity) throws PersistentException {

    }

    @Override
    public void delete(Integer id) throws PersistentException {

    }
}
