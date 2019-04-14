package by.training.lakes_paradise.db.dao;

import by.training.lakes_paradise.db.entity.Image;

import java.util.List;

public interface ImageDao extends Dao<Image> {
    List<Image> read();
}
