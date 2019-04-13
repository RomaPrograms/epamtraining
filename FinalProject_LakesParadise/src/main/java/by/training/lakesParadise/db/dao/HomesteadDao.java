package by.training.lakesParadise.db.dao;

import by.training.lakesParadise.entity.Homestead;

public interface HomesteadDao extends Dao<Homestead>{
    Homestead readByTitle(String search);


}
