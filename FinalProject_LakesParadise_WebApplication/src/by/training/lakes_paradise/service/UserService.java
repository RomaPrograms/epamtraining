package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

public interface UserService extends Service {
    List<User> read() throws PersistentException;

    Integer create(final User user) throws PersistentException;

    User read(final Integer id) throws PersistentException;

    void update(final User user) throws PersistentException;

    void delete(final Integer id) throws PersistentException;
}
