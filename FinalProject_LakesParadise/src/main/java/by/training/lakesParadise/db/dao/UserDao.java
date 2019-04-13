package by.training.lakesParadise.db.dao;

import by.training.lakesParadise.entity.User;

import java.util.List;

public interface UserDao extends Dao<User>{
    User read(String login, String password);

    List<User> read();
}
