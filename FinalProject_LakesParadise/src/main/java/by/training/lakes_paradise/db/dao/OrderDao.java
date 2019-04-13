package by.training.lakes_paradise.db.dao;

import by.training.lakes_paradise.entity.Order;

import java.util.List;

public interface OrderDao extends Dao<Order>{
    List<Order> readByProfile(Integer profileId);
    List<Order> readByHomestead(Integer homesteadId);

    List<Order> read();
}
