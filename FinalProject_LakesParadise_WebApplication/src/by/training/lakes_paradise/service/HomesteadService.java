package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.exception.PersistentException;

import java.math.BigDecimal;
import java.util.List;

public interface HomesteadService extends Service {
    List<Homestead> readAllByTitle(String search) throws PersistentException;

    List<Homestead> readAllByPrice(BigDecimal minPrice, BigDecimal maxPrice)
            throws PersistentException;

    List<Homestead> readByOwner(int ownerId) throws PersistentException;

    List<Homestead> readAll() throws PersistentException;

    Integer create(Homestead homestead) throws PersistentException;

    Homestead readById(Integer id) throws PersistentException;

    void update(Homestead entity) throws PersistentException;

    void delete(Integer id) throws PersistentException;
}
