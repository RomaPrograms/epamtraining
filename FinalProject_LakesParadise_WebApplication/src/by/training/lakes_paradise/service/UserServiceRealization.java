package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.dao.ProfileDao;
import by.training.lakes_paradise.db.dao.UserDao;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.db.mysql.ProfileDaoRealization;
import by.training.lakes_paradise.db.mysql.UserDaoRealization;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

public class UserServiceRealization extends ServiceRealization
        implements UserService {
    @Override
    public List<User> readAll() throws PersistentException {
        return null;
    }

    @Override
    public Integer create(final User user) throws PersistentException {
        ProfileDao profileDao = transaction.createDao(ProfileDao.class);
        UserDao userDao = transaction.createDao(UserDao.class);
        Profile profile = new Profile();
        profile.setLogin(user.getLogin());
        profile.setPassword(user.getPassword());
        profile.setRole(user.getRole());
        int profileId = profileDao.create(profile);
        user.setId(profileId);
        return userDao.create(user);
    }

    @Override
    public User read(final Integer id) throws PersistentException {
        UserDao userDao = transaction.createDao(UserDao.class);
        return userDao.read(id);
    }

    @Override
    public void update(final User user) throws PersistentException {
        ProfileDao profileDao = transaction.createDao(ProfileDao.class);
        UserDao userDao = transaction.createDao(UserDao.class);
        Profile profile = new Profile();
        profile.setLogin(user.getLogin());
        profile.setPassword(user.getPassword());
        profile.setId(user.getId());
        profileDao.update(profile);
        userDao.update(user);
    }

    @Override
    public void delete(final Integer id) throws PersistentException {

    }
}
