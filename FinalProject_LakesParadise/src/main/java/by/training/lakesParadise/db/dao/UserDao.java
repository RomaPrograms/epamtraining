package by.training.lakesParadise.db.dao;

import by.training.lakesParadise.entity.User;

import java.util.List;

public interface UserDao extends Dao<User>{
    List<User> read();
}
