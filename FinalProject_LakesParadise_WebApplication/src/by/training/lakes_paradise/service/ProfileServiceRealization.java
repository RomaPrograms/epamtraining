package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.dao.ProfileDao;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

/**
 * Class with methods for working throw the DAO with "profiles" table.
 */
public class ProfileServiceRealization extends ServiceRealization
        implements ProfileService {

    /**
     * Method returns profile of user by login and password.
     *
     * @param login    - login of user
     * @param password - profile of user
     * @return profile of user
     */
    @Override
    public Profile read(final String login,
                        final String password) throws PersistentException {
        ProfileDao profileDao = transaction.createDao(ProfileDao.class);
        return profileDao.read(login, password);
    }

    /**
     * Method reads all objects from "profiles" table.
     *
     * @return objects from "profiles" table
     */
    @Override
    public List<Profile> readAll() throws PersistentException {
        return null;
    }

    /**
     * Method adds new object to "profiles" table.
     *
     * @param profile - new object
     * @return - id of new object in database
     * @throws PersistentException - exception with adding object to database
     */
    @Override
    public Integer create(final Profile profile) throws PersistentException {
        ProfileDao profileDao = transaction.createDao(ProfileDao.class);
        return profileDao.create(profile);
    }

    /**
     * Method reads object from "profiles" table by id.
     *
     * @param id - id of object
     * @return object which was read
     * @throws PersistentException - exception with reading object from database
     */
    @Override
    public Profile read(final Integer id) throws PersistentException {
        ProfileDao profileDao = transaction.createDao(ProfileDao.class);
        return profileDao.read(id);
    }

    /**
     * Method updates object in "profiles" table by id.
     *
     * @param profile - updated object
     */
    @Override
    public void update(final Profile profile) throws PersistentException {
        ProfileDao profileDao = transaction.createDao(ProfileDao.class);
        profileDao.update(profile);
    }

    /**
     * Method deletes object in "profiles" table by id.
     *
     * @param id - id of object for deletion
     */
    @Override
    public void delete(final Integer id) throws PersistentException {
        ProfileDao profileDao = transaction.createDao(ProfileDao.class);
        profileDao.delete(id);
    }
}
