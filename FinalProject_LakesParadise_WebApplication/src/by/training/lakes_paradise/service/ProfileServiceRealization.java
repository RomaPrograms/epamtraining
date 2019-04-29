package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.mysql.ProfileDaoRealization;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

public class ProfileServiceRealization extends ServiceRealization
        implements ProfileService{

    @Override
    public Profile read(String login, String password) throws PersistentException {
        return null;
    }

    @Override
    public List<Profile> read() throws PersistentException {
        return null;
    }

    @Override
    public Integer create(Profile profile) throws PersistentException {
        ProfileDaoRealization profileDaoRealization
                = new ProfileDaoRealization();
        return profileDaoRealization.create(profile);
    }

    @Override
    public Profile read(Integer id) throws PersistentException {
        return null;
    }

    @Override
    public void update(Profile profile) throws PersistentException {

    }

    @Override
    public void delete(Integer id) throws PersistentException {

    }
}
