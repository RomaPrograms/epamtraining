package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.mysql.ProfileDaoRealization;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

public class ProfileServiceRealization extends ServiceRealization
        implements ProfileService {

    @Override
    public Profile read(final String login,
                        final String password) throws PersistentException {
        return null;
    }

    @Override
    public List<Profile> read() throws PersistentException {
        return null;
    }

    @Override
    public Integer create(final Profile profile) throws PersistentException {
        ProfileDaoRealization profileDaoRealization
                = new ProfileDaoRealization();
        return profileDaoRealization.create(profile);
    }

    @Override
    public Profile read(final Integer id) throws PersistentException {
        return null;
    }

    @Override
    public void update(final Profile profile) throws PersistentException {

    }

    @Override
    public void delete(final Integer id) throws PersistentException {

    }
}
