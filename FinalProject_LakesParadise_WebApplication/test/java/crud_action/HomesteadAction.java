package crud_action;

import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.mysql.TransactionFactoryRealization;
import by.training.lakes_paradise.db.pool.ConnectionPoolRealization;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.HomesteadService;
import by.training.lakes_paradise.service.ServiceFactory;
import by.training.lakes_paradise.service.ServiceFactoryRealization;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class HomesteadAction {

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

    private HomesteadService homesteadService;

    @BeforeClass
    public void initialiseParsingAction() {
        try {
            ConnectionPoolRealization.getInstance().init(DB_DRIVER_CLASS,
                    DB_URL, DB_USER, DB_PASSWORD, DB_POOL_START_SIZE,
                    DB_POOL_MAX_SIZE, DB_POOL_CHECK_CONNECTION_TIMEOUT);

            TransactionFactoryRealization transaction
                    = new TransactionFactoryRealization();
            ServiceFactory serviceFactory
                    = new ServiceFactoryRealization(transaction);
            homesteadService
                    = serviceFactory.getService(HomesteadService.class);

        } catch (PersistentException e) {
            LOGGER.error("It is impossible to initialize application", e);
        }
    }

    @DataProvider()
    public Object[] dataProviderForReadByIdAction() {
        Homestead homestead = new Homestead();
        return new Object[]{homestead};
    }

    @Test(dataProvider = "dataProviderForReadByIdAction")
    public void readByIdAction(Homestead expectedHomestead) {
        try {
            Homestead homestead = homesteadService.readById(2);

        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @Test(dataProvider = "dataProviderForReadByIdAction")
    public void readAllHomesteadsAction(Homestead expectedHomestead) {
        try {
            List<Homestead> homestead = homesteadService.readAll();

        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }
}
