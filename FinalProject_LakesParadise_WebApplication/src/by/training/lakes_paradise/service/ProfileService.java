package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

public interface ProfileService extends Service {
    Profile read(final String login, final String password) throws PersistentException;

    List<Profile> read() throws PersistentException;

    Integer create(final Profile profile) throws PersistentException;

    Profile read(final Integer id) throws PersistentException;

    void update(final Profile profile) throws PersistentException;

    void delete(final Integer id) throws PersistentException;
}
