package by.training.lakes_paradise.db.dao;

import by.training.lakes_paradise.entity.User;

import java.util.List;

public interface UserDao extends Dao<User>{
    List<User> read();
}
