package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.exception.PersistentException;

import java.math.BigDecimal;
import java.util.List;

public interface HomesteadService extends Service {
    List<Homestead> findAllByTitle(String search) throws PersistentException;

    List<Homestead> findAllByPrice(BigDecimal minPrice, BigDecimal maxPrice)
            throws PersistentException;

    List<Homestead> findByOwner(int ownerId) throws PersistentException;

    List<Homestead> findAll() throws PersistentException;

    Integer add(Homestead homestead) throws PersistentException;

    Homestead findById(Integer id) throws PersistentException;

    void update(Homestead entity) throws PersistentException;

    void delete(Integer id) throws PersistentException;
}
