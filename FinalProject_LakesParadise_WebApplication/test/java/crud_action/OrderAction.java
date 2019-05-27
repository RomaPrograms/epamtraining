package crud_action;

import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.entity.Order;
import by.training.lakes_paradise.db.entity.Review;
import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.db.mysql.TransactionFactoryRealization;
import by.training.lakes_paradise.db.pool.ConnectionPoolRealization;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderAction {

    private static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/"
            + "lakes_paradise_db?useUnicode=true&characterEncoding=UTF-8";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "9512684Roma";
    private static final int DB_POOL_START_SIZE = 10;
    private static final int DB_POOL_MAX_SIZE = 1000;

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(OrderAction.class);
    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private OrderService orderService;
    private UserService userService;
    private HomesteadService homesteadService;
    private int createdOrderId;
    private int createdUserId;
    private int createdHomesteadId;
    private Order order1 = new Order();
    private Order order2 = new Order();
    private Order order3 = new Order();
    private Order order4 = new Order();

    @BeforeClass
    public void initialiseParsingAction() {
        try {
            ConnectionPoolRealization.getInstance().init(DB_DRIVER_CLASS,
                    DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE,
                    DB_POOL_MAX_SIZE);

            TransactionFactoryRealization transaction
                    = new TransactionFactoryRealization();
            ServiceFactory serviceFactory
                    = new ServiceFactoryRealization(transaction);
            orderService
                    = serviceFactory.getService(OrderService.class);
            userService
                    = serviceFactory.getService(UserService.class);
            homesteadService
                    = serviceFactory.getService(HomesteadService.class);

            User user = new User();
            user.setId(1);
            user.setLogin("user1");
            Homestead homestead = new Homestead();
            homestead.setId(2);
            homestead.setTitle("Dacha_2");
            order1.setId(1);
            order1.setUser(user);
            order1.setHomestead(homestead);
            Date date = format.parse("2019-06-15");
            order1.setStartRenting(date);
            date = format.parse("2019-06-25");
            order1.setEndRenting(date);

            user = new User();
            user.setId(2);
            user.setLogin("user2");
            homestead = new Homestead();
            homestead.setId(1);
            homestead.setTitle("Dacha_1");
            order2.setId(2);
            order2.setUser(user);
            order2.setHomestead(homestead);
            date = format.parse("2019-07-15");
            order2.setStartRenting(date);
            date = format.parse("2019-07-25");
            order2.setEndRenting(date);

            user = new User();
            user.setId(4);
            user.setLogin("user4");
            homestead = new Homestead();
            homestead.setId(3);
            homestead.setTitle("Dacha_3");
            order3.setId(3);
            order3.setUser(user);
            order3.setHomestead(homestead);
            date = format.parse("2019-08-15");
            order3.setStartRenting(date);
            date = format.parse("2019-08-25");
            order3.setEndRenting(date);

            user = new User();
            user.setId(4);
            user.setLogin("user4");
            homestead = new Homestead();
            homestead.setId(4);
            homestead.setTitle("Dacha_4");
            order4.setId(4);
            order4.setUser(user);
            order4.setHomestead(homestead);
            date = format.parse("2019-09-15");
            order4.setStartRenting(date);
            date = format.parse("2019-09-25");
            order4.setEndRenting(date);

        } catch (PersistentException | ParseException e) {
            LOGGER.error("It is impossible to initialize application", e);
        }
    }

    @DataProvider(name = "dataProviderForReadOrdersByHomesteadAction")
    public Object[] dataProviderForReadOrdersByHomesteadAction() {
        List<Order> orders1 = new ArrayList<>();
        orders1.add(order2);
        List<Order> orders2 = new ArrayList<>();
        orders2.add(order3);
        return new Object[][]{{orders1, 1}, {orders2, 3}};
    }

    @Test(dataProvider = "dataProviderForReadOrdersByHomesteadAction")
    public void readOrdersByHomesteadAction(List<Order> expectedOrders,
                                            int homesteadId) {
        try {
            List<Order> actualOrders
                    = orderService.readByHomestead(homesteadId);
            Assert.assertEquals(actualOrders, expectedOrders);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }


    @DataProvider(name = "dataProviderForReadOrdersByOwnerAction")
    public Object[] dataProviderForReadOrdersByOwnerAction() {
        List<Order> orders1 = new ArrayList<>();
        order2.getUser().setPhone(375296222263L);
        orders1.add(order2);
        order1.getUser().setPhone(375291878912L);
        orders1.add(order1);
        List<Order> orders2 = new ArrayList<>();
        order3.getUser().setPhone(375291456734L);
        orders2.add(order3);
        order4.getUser().setPhone(375291456734L);
        orders2.add(order4);
        return new Object[][]{{orders1, 5}, {orders2, 6}};
    }

    @Test(dataProvider = "dataProviderForReadOrdersByOwnerAction",
            priority = 6)
    public void readOrdersByOwnerAction(List<Order> expectedOrders,
                                        int ownerId) {
        try {
            List<Order> actualOrders
                    = orderService.readByOwner(ownerId);
            Assert.assertEquals(actualOrders, expectedOrders);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "dataProviderForReadOrdersByProfileAction")
    public Object[] dataProviderForReadOrdersByProfileAction() {
        List<Order> orders1 = new ArrayList<>();
        orders1.add(order2);
        List<Order> orders2 = new ArrayList<>();
        orders2.add(order3);
        orders2.add(order4);
        return new Object[][]{{orders1, 2}, {orders2, 4}};
    }

    @Test(dataProvider = "dataProviderForReadOrdersByProfileAction",
            priority = 1)
    public void readOrdersByProfileAction(List<Order> expectedOrders,
                                          int profileId) {
        try {
            List<Order> actualOrders
                    = orderService.readByProfile(profileId);
            Assert.assertEquals(actualOrders, expectedOrders);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "dataProviderForReadOrdersByIdAction")
    public Object[] dataProviderForReadOrdersByIdAction() {
        return new Object[][]{{order1, 1}, {order2, 2}};
    }

    @Test(dataProvider = "dataProviderForReadOrdersByIdAction",
            priority = 2)
    public void readOrdersByIdAction(Order expectedOrder, int id) {
        try {
            Order actualOrder = orderService.read(id);
            Assert.assertEquals(actualOrder, expectedOrder);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "dataProviderForCreateOrderAction")
    public Object[] dataProviderForCreateOrderAction() {
        Order expectedOrder = new Order();
        try {
            User user = new User();
            user.setId(1);
            user.setLogin("user1");
            Homestead homestead = new Homestead();
            homestead.setId(1);
            homestead.setTitle("Dacha_1");
            expectedOrder.setId(1);
            expectedOrder.setUser(user);
            expectedOrder.setHomestead(homestead);
            Date date = format.parse("2019-10-15");
            expectedOrder.setStartRenting(date);
            date = format.parse("2019-10-25");
            expectedOrder.setEndRenting(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Object[]{expectedOrder};
    }

    @Test(dataProvider = "dataProviderForCreateOrderAction",
            priority = 3)
    public void createOrderAction(Order expectedOrder) {
        try {
            createdOrderId = orderService.create(expectedOrder);
            expectedOrder.setId(createdOrderId);
            Order actualOrder = orderService.read(createdOrderId);
            Assert.assertEquals(actualOrder, expectedOrder);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "dataProviderForUpdateOrderAction")
    public Object[] dataProviderForUpdateOrderAction() {
        Order expectedOrder = null;
        try {
            expectedOrder = orderService.read(createdOrderId);
            expectedOrder.getUser().setId(3);
            expectedOrder.getUser().setLogin("user3");
        } catch (PersistentException e) {
            e.printStackTrace();
        }
        return new Object[][]{{expectedOrder, createdOrderId}};
    }

    @Test(dataProvider = "dataProviderForUpdateOrderAction",
            priority = 4)
    public void updateOrderAction(Order expectedOrder, int id) {
        try {
            orderService.update(expectedOrder);
            Order actualOrder = orderService.read(id);
            Assert.assertEquals(actualOrder, expectedOrder);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @DataProvider(name = "dataProviderForDeleteOrderAction")
    public Object[] dataProviderForDeleteOrderAction() {
        return new Object[]{createdOrderId};
    }

    @Test(dataProvider = "dataProviderForDeleteOrderAction",
            priority = 5)
    public void deleteOrderAction(int id) {
        try {
            orderService.delete(id);
            Order actualOrder = orderService.read(id);
            Assert.assertNull(actualOrder);
        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }
}