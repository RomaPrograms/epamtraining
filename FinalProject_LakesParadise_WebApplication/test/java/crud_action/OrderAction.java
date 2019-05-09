package crud_action;

import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.entity.Order;
import by.training.lakes_paradise.db.mysql.TransactionFactoryRealization;
import by.training.lakes_paradise.db.pool.ConnectionPoolRealization;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.OrderService;
import by.training.lakes_paradise.service.ServiceFactory;
import by.training.lakes_paradise.service.ServiceFactoryRealization;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class OrderAction {

    private static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/lakes_paradise_db?useUnicode=true&characterEncoding=UTF-8";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "9512684Roma";
    private static final int DB_POOL_START_SIZE = 10;
    private static final int DB_POOL_MAX_SIZE = 1000;
    private static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(HomesteadAction.class);

    private OrderService orderService;

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

        } catch (PersistentException e) {
            LOGGER.error("It is impossible to initialize application", e);
        }
    }

    @DataProvider(name = "dataProviderForReadAllOrdersByHomesteadAction")
    public Object[] dataProviderForReadAllOrdersByHomesteadAction() {
        Order order = new Order();
        return new Object[]{order};
    }

    @Test(dataProvider = "dataProviderForReadAllOrdersByHomesteadAction")
    public void readOrderByIdAction(Order expectedOrder) {
        try {
            Order order = orderService.read(19);

        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @Test(dataProvider = "dataProviderForReadAllOrdersByHomesteadAction")
    public void readAllOrdersByHomesteadAction(Order expectedOrder) {
        try {
            List<Order> orders = orderService.readByHomestead(2);

        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }
}