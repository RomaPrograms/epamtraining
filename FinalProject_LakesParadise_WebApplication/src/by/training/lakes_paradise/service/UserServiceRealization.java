package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.dao.ProfileDao;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.db.mysql.ProfileDaoRealization;
import by.training.lakes_paradise.db.mysql.UserDaoRealization;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

public class UserServiceRealization extends ServiceRealization
        implements UserService{
    @Override
    public List<User> read() throws PersistentException {
        return null;
    }

    @Override
    public Integer create(User user) throws PersistentException {
        ProfileDaoRealization profileDaoRealization = new ProfileDaoRealization();
        UserDaoRealization userDaoRealization = new UserDaoRealization();
        Profile profile = new Profile();
        profile.setLogin(user.getLogin());
        profile.setPassword(user.getPassword());
        profile.setRole(user.getRole());
        int profileId = profileDaoRealization.create(profile);
        user.setId(profileId);
        return userDaoRealization.create(user);
    }

    @Override
    public User read(Integer id) throws PersistentException {
        return null;
    }

    @Override
    public void update(User user) throws PersistentException {

    }

    @Override
    public void delete(Integer id) throws PersistentException {

    }
}
