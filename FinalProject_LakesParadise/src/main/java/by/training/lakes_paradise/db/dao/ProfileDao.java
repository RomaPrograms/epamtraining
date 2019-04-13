package by.training.lakes_paradise.db.dao;

import by.training.lakes_paradise.entity.Profile;

import java.util.List;

public interface ProfileDao extends Dao<Profile> {
    List<Profile> read();
    Profile read(String login, String password);
}
