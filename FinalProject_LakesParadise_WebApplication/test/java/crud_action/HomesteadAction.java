package crud_action;

import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.entity.Image;
import by.training.lakes_paradise.db.entity.Review;
import by.training.lakes_paradise.db.entity.User;
import by.training.lakes_paradise.db.mysql.TransactionFactoryRealization;
import by.training.lakes_paradise.db.pool.ConnectionPoolRealization;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.HomesteadService;
import by.training.lakes_paradise.service.ServiceFactory;
import by.training.lakes_paradise.service.ServiceFactoryRealization;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.util.List;

public class HomesteadAction {

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
            = LogManager.getLogger(HomesteadAction.class);

    private HomesteadService homesteadService;
    private Homestead homestead1 = new Homestead();
    private Homestead homestead2 = new Homestead();
    private Homestead homestead3 = new Homestead();
    private Homestead homestead4 = new Homestead();

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
            homesteadService
                    = serviceFactory.getService(HomesteadService.class);
            User user = new User();

            homestead1.setId(1);
            homestead1.setPrice(new BigDecimal(300));
            homestead1.setTitle("Dacha_1");
            homestead1.setDescription("description_1");
            homestead1.setPeopleNumber(5);
            user.setId(5);
            user.setName("Pasha");
            user.setSurname("Struk");
            user.setPhone(375293434567L);
            homestead1.setOwner(user);

            homestead2.setId(2);
            homestead2.setPrice(new BigDecimal(400));
            homestead2.setTitle("Dacha_2");
            homestead2.setDescription("description_2");
            homestead2.setPeopleNumber(6);
            user.setId(5);
            user.setName("Pasha");
            user.setSurname("Struk");
            user.setPhone(375293434567L);
            homestead2.setOwner(user);

            homestead3.setId(3);
            homestead3.setPrice(new BigDecimal(500));
            homestead3.setTitle("Dacha_3");
            homestead3.setDescription("description_3");
            homestead3.setPeopleNumber(7);
            user.setId(6);
            user.setName("Slava");
            user.setSurname("Kalinin");
            user.setPhone(375292345655L);
            homestead3.setOwner(user);

            homestead4.setId(4);
            homestead4.setPrice(new BigDecimal(600));
            homestead4.setTitle("Dacha_4");
            homestead4.setDescription("description_4");
            homestead4.setPeopleNumber(8);
            user.setId(6);
            user.setName("Slava");
            user.setSurname("Kalinin");
            user.setPhone(375292345655L);
            homestead4.setOwner(user);

        } catch (PersistentException e) {
            LOGGER.error("It is impossible to initialize application", e);
        }
    }

    @DataProvider()
    public Object[] dataProviderForReadByIdAction() {
        return new Object[][]{{homestead1, 1}};
    }

    @Test(dataProvider = "dataProviderForReadByIdAction")
    public void readByIdAction(Homestead expectedHomestead, int id) {
        try {
            Homestead actualHomestead = homesteadService.readById(id);
            Assert.assertEquals(expectedHomestead, actualHomestead);
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

    @Test()
    public void readAllHomesteadsByTitleAction() {
        try {
            List<Homestead> homestead = homesteadService.readAllByTitle("Da");

        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }

    @Test()
    public void readAllHomesteadsByPriceAction() {
        try {
            BigDecimal minPrice = new BigDecimal(150);
            BigDecimal maxPrice = new BigDecimal(200);
            List<Homestead> homestead = homesteadService
                    .readAllByPrice(minPrice, maxPrice);

        } catch (PersistentException e) {
            e.printStackTrace();
        }
    }
}
