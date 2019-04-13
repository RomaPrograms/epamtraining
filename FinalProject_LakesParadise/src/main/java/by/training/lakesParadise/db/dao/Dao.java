package by.training.lakesParadise.db.dao;

import by.training.lakesParadise.entity.Entity;

import java.util.List;

public interface Dao<Type extends Entity> {
    Integer create(Type entity);

    Type read(Integer id);

    void update(Type entity);

    void delete(Integer id);
}
