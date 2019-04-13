package by.training.lakesParadise.db.dao;

import by.training.lakesParadise.entity.Homestead;

import java.math.BigDecimal;
import java.util.List;

public interface HomesteadDao extends Dao<Homestead>{
    List<Homestead> findByTitle(String search);
    List<Homestead> findByPrice(BigDecimal minPrice, BigDecimal maxPrice);
    List<Homestead> read();
}
