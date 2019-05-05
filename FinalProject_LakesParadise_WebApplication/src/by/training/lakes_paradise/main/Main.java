package by.training.lakes_paradise.main;

import by.training.lakes_paradise.db.entity.*;
import by.training.lakes_paradise.db.mysql.*;
import by.training.lakes_paradise.db.pool.ConnectionPoolRealization;
import by.training.lakes_paradise.exception.PersistentException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

        try {
            /*ImageDaoRealization imageDaoRealization = new ImageDaoRealization();
            Image image = new Image();
            image.setPathToImage("web/img/");
            image.setImageName("1.1_farmstead.jpg");
            imageDaoRealization.createNewVersion(image);*/

//            File file = new File("D:\\инфа\\EPAM\\05_JavaST_2019\\FinalProject_LakesParadise_WebApplication\\web\\img\\1.1_farmstead.jpg");
//            BufferedImage image1 = ImageIO.read(file);
//            File outputFile = new File("web/img/" + "1.1_farmstead.jpg");
//            ImageIO.write(image1, "png", outputFile);

            String photoAddress = "D:\\инфа\\EPAM\\05_JavaST_2019\\FinalProject_LakesParadise_WebApplication\\web\\img\\1.3_farmstead.jpg";
            String photoName = "1.3_farmstead.jpg";
            File file = new File(photoAddress);
            BufferedImage image1 = ImageIO.read(file);
            File outputFile = new File("web/img/" + photoName);
            ImageIO.write(image1, "png", outputFile);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        //        for (var image : imageDaoRealization.readImagesByHomeId(2)) {
//            System.out.println(image);
//        }
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
                image1.setHomesteadId(4);

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
//        System.out.println(profileDao.read("pasha@gmail.com", "111"));
        ConnectionPoolRealization.getInstance().init(DB_DRIVER_CLASS, DB_URL, DB_USER,
                DB_PASSWORD, DB_POOL_START_SIZE, DB_POOL_MAX_SIZE,
                DB_POOL_CHECK_CONNECTION_TIMEOUT);

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
        //System.out.println(new HomesteadDaoRealization().read(2));
    }
}
