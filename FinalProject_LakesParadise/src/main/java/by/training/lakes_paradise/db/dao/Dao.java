package by.training.lakes_paradise.db.dao;

import by.training.lakes_paradise.entity.Entity;

public interface Dao<Type extends Entity> {
    Integer create(Type entity);

    Type read(Integer id);

    void update(Type entity);

    void delete(Integer id);
}
