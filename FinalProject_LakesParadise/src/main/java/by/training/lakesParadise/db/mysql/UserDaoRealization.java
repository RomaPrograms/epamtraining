package by.training.lakesParadise.db.mysql;

import by.training.lakesParadise.db.dao.UserDao;
import by.training.lakesParadise.entity.User;

import java.util.List;

public class UserDaoRealization implements UserDao {
    @Override
    public User read(String login, String password) {
        return null;
    }

    @Override
    public List<User> read() {
        return null;
    }

    @Override
    public Integer create(User entity) {
        return null;
    }

    @Override
    public User read(Integer id) {
        return null;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public void delete(Integer id) {

    }
}
