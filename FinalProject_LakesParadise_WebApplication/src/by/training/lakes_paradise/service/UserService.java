package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

public interface UserService extends Service {
    List<User> read() throws PersistentException;

    Integer create(User user) throws PersistentException;

    User read(Integer id) throws PersistentException;

    void update(User user) throws PersistentException;

    void delete(Integer id) throws PersistentException;
}
