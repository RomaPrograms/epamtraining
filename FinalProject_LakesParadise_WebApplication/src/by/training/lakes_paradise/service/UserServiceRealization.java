package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.dao.ProfileDao;
import by.training.lakes_paradise.db.dao.UserDao;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.ArrayList;
import java.util.List;

/**
 * Class with methods for working throw the DAO with "users" table.
 */
public class UserServiceRealization extends ServiceRealization
        implements UserService {

    /**
     * Method that reads all objects from "users" table.
     *
     * @return list with objects from "users" table
     */
    @Override
    public List<User> readAll() throws PersistentException {
        UserDao userDao = transaction.createDao(UserDao.class);
        return userDao.read();
    }

    @Override
    public List<User> readByLogin(String login) throws PersistentException {
        UserDao userDao = transaction.createDao(UserDao.class);
        return userDao.readByLogin(login);
    }

    /**
     * Method adds new user to "users" table.
     *
     * @param user - new object
     * @return - id of new object in database
     * @throws PersistentException - exception with adding object to database
     */
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

    /**
     * Method reads user from "users" table by id.
     *
     * @param id - id of object
     * @return object which was read
     * @throws PersistentException - exception with reading object from database
     */
    @Override
    public User read(final Integer id) throws PersistentException {
        UserDao userDao = transaction.createDao(UserDao.class);
        return userDao.read(id);
    }

    /**
     * Method updates user from "users" table by id.
     *
     * @param user - updated object
     */
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

    /**
     * Method deletes user from "users" table by id.
     *
     * @param id - id of object for deletion
     */
    @Override
    public void delete(final Integer id) throws PersistentException {
        UserDao userDao = transaction.createDao(UserDao.class);
        ProfileDao profileDao = transaction.createDao(ProfileDao.class);
        userDao.delete(id);
        profileDao.delete(id);
    }
}
