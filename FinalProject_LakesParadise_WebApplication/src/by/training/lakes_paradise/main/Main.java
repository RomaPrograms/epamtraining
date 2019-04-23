package by.training.lakes_paradise.main;

import by.training.lakes_paradise.db.ConnectionDB;
import by.training.lakes_paradise.db.dao.HomesteadDao;
import by.training.lakes_paradise.db.dao.OrderDao;
import by.training.lakes_paradise.db.dao.ProfileDao;
import by.training.lakes_paradise.db.dao.TransactionFactory;
import by.training.lakes_paradise.db.entity.*;
import by.training.lakes_paradise.db.mysql.*;
import by.training.lakes_paradise.db.pool.ConnectionPool;
import by.training.lakes_paradise.db.pool.ConnectionPoolRealization;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.HomesteadService;
import by.training.lakes_paradise.service.ServiceFactoryRealization;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/lakes_paradise_db?useUnicode=true&characterEncoding=UTF-8";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "9512684Roma";
    private static final int DB_POOL_START_SIZE = 10;
    private static final int DB_POOL_MAX_SIZE = 1000;
    private static final int DB_POOL_CHECK_CONNECTION_TIMEOUT = 0;

    public static void main(final String[] args) throws PersistentException,
            ParseException {

        /*ImageDaoRealization imageDaoRealization = new ImageDaoRealization();
        for (var image : imageDaoRealization.readImagesByHomeId(2)) {
            System.out.println(image);
        }*/
        /*try {
            Connection connection = ConnectionDB.getConnection();
            imageDaoRealization.delete(3);
            List<String> list = new ArrayList<>();
            list.add("www/img/1.1_farmstead.jpg");
            list.add("www/img/1.2_farmstead.jpg");
            list.add("www/img/1.3_farmstead.jpg");
            list.add("www/img/2.0_farmstead.jpg");
            list.add("www/img/2.1_farmstead.jpg");
            list.add("www/img/2.2_farmstead.jpg");
            list.add("www/img/2.3_farmstead.jpg");
            list.add("www/img/3.0_farmstead.jpg");
            list.add("www/img/3.1_farmstead.jpg");
            list.add("www/img/3.2_farmstead.jpg");
            list.add("www/img/4.0_farmstead.jpg");
            list.add("www/img/4.1_farmstead.jpg");
            list.add("www/img/4.2_farmstead.jpg");
            for(int i = 10; i < 13; i++) {
                BufferedImage image = ImageIO
                        .read(new File(list.get(i)));
                Blob blob = connection.createBlob();
                try (OutputStream outputStream = blob.setBinaryStream(1)) {
                    ImageIO.write(image, "jpg", outputStream);
                }

                Image image1 = new Image();
                image1.setImage(blob);
                image1.setHomeId(4);

                imageDaoRealization.create(image1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        //OrderDaoRealization orderDaoRealization = new OrderDaoRealization();
//        List<Order> orders = orderDaoRealization.read();
//        for(var order : orders) {
//            System.out.println(order);
//        }

//        System.out.println(orderDaoRealization.read(2));
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        orderDaoRealization.delete(4);
        UserDaoRealization userDao = new UserDaoRealization();
//        for(var user : userDao.read()) {
//            System.out.println(user);
//        }
        ProfileDaoRealization profileDao = new ProfileDaoRealization();
//        for(var profile : profileDao.read()) {
//            System.out.println(profile);
//        }
//        System.out.println(profileDao.read("luda@gmail.com", "333"));
//        Profile profile = new Profile();
//        profile.setId(8);
//        profile.setLogin("login1");
//        profile.setPassword("password1");
//        profile.setRole(Role.ADMINISTRATOR);
//        profileDao.create(profile);
//        ReviewDaoRealization reviewDaoRealization
// = new ReviewDaoRealization();
//        for(var review : reviewDaoRealization.readReviewsByHomeId(1)) {
//            System.out.println(review);
//        }

//        ConnectionPoolRealization.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER,
//                DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE,
//                DB_POOL_CHECK_CONNECTION_TIMEOUT);

        //ServiceFactoryRealization factoryRealization = new ServiceFactoryRealization(new TransactionFactoryRealization());
        /*TransactionFactoryRealization transactionFactoryRealization = new TransactionFactoryRealization();
        List<Homestead> list = null;
        try {
            list = transactionFactoryRealization.createTransaction().createDao(HomesteadDao.class).read();
        } catch (PersistentException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }*/
        //ServiceFactoryRealization factoryRealization = new ServiceFactoryRealization(new TransactionFactoryRealization());

        //System.out.println(factoryRealization.getService(HomesteadService.class).findAll());
        System.out.println(new HomesteadDaoRealization().read(2));
    }
}
