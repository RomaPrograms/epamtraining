package by.training.lakesParadise.db.dao;

import by.training.lakesParadise.entity.Profile;
import by.training.lakesParadise.entity.User;

import java.util.List;

public interface ProfileDao extends Dao<Profile>{
    Profile read(String login, String password);

    List<Profile> read();
}
