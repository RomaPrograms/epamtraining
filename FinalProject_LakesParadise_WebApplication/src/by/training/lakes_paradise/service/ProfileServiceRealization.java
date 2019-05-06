package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.dao.ProfileDao;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.mysql.ProfileDaoRealization;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

public class ProfileServiceRealization extends ServiceRealization
        implements ProfileService {

    @Override
    public Profile read(final String login,
                        final String password) throws PersistentException {
        ProfileDao profileDao = transaction.createDao(ProfileDao.class);
        return profileDao.read(login, password);
    }

    @Override
    public List<Profile> read() throws PersistentException {
        return null;
    }

    @Override
    public Integer create(final Profile profile) throws PersistentException {
        ProfileDao profileDao = transaction.createDao(ProfileDao.class);
        return profileDao.create(profile);
    }

    @Override
    public Profile read(final Integer id) throws PersistentException {
        ProfileDao profileDao = transaction.createDao(ProfileDao.class);
        return profileDao.read(id);
    }

    @Override
    public void update(final Profile profile) throws PersistentException {

    }

    @Override
    public void delete(final Integer id) throws PersistentException {

    }
}
