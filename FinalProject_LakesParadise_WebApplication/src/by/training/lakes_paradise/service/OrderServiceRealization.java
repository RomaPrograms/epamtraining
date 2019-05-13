package by.training.lakes_paradise.service;

import by.training.lakes_paradise.db.dao.OrderDao;
import by.training.lakes_paradise.db.dao.UserDao;
import by.training.lakes_paradise.db.entity.Order;
import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.exception.PersistentException;

import java.util.List;

/**
 * Class with methods for working throw the DAO with "orders" table.
 */
public class OrderServiceRealization extends ServiceRealization
        implements OrderService {

    /**
     * Method that search all orders by id of profile.
     *
     * @param profileId - id of profile
     * @return list with orders which were done by expected profile
     * @throws PersistentException - exception with searching in orders table
     */
    @Override
    public List<Order> readByProfile(final Integer profileId)
            throws PersistentException {
        OrderDao orderDaoRealization = transaction.createDao(OrderDao.class);
        return orderDaoRealization.readByProfile(profileId);
    }

    @Override
    public List<Order> readByOwner(Integer ownerId) throws PersistentException {
        OrderDao orderDaoRealization = transaction.createDao(OrderDao.class);
        List<Order> orders = orderDaoRealization.readByOwner(ownerId);
        UserDao userDaoRealization = transaction.createDao(UserDao.class);
        for(var order : orders) {
            User orderUser = order.getUser();
            User user = userDaoRealization.read(orderUser.getId());
            orderUser.setPhone(user.getPhone());
        }
        return orders;
    }

    /**
     * Method that search all orders by id of homestead.
     *
     * @param homesteadId - id of homestead
     * @return list with orders which were done with expected homestead
     * @throws PersistentException - exception with searching in orders table
     */
    @Override
    public List<Order> readByHomestead(final Integer homesteadId)
            throws PersistentException {
        OrderDao orderDaoRealization = transaction.createDao(OrderDao.class);
        return orderDaoRealization.readByHomestead(homesteadId);
    }

    /**
     * Method that reads all objects from "orders" table.
     *
     * @return list with objects from "orders" table
     * @throws PersistentException - exception with searching in orders table
     */
    @Override
    public List<Order> readAll() throws PersistentException {
        return null;
    }

    /**
     * Method adds new object to "orders" table.
     *
     * @param order - new object
     * @return - id of new object in database
     * @throws PersistentException - exception with adding object to database
     */
    @Override
    public Integer create(final Order order) throws PersistentException {
        OrderDao orderDaoRealization = transaction.createDao(OrderDao.class);
        return orderDaoRealization.create(order);
    }

    /**
     * Method reads object from "orders" table by id.
     *
     * @param id - id of object
     * @return object which was read
     * @throws PersistentException - exception with reading object from database
     */
    @Override
    public Order read(final Integer id) throws PersistentException {
        return null;
    }

    /**
     * Method updates object in "orders" table by id.
     *
     * @param order - updated object
     */
    @Override
    public void update(final Order order) throws PersistentException {

    }

    /**
     * Method deletes object in "orders" table by id.
     *
     * @param id - id of object for deletion
     */
    @Override
    public void delete(final Integer id) throws PersistentException {

    }
}
