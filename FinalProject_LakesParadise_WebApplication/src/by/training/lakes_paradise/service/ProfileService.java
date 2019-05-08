package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

public interface ProfileService extends Service {
    Profile read(String login, String password) throws PersistentException;

    List<Profile> readAll() throws PersistentException;

    Integer create(Profile profile) throws PersistentException;

    Profile read(Integer id) throws PersistentException;

    void update(Profile profile) throws PersistentException;

    void delete(Integer id) throws PersistentException;
}
