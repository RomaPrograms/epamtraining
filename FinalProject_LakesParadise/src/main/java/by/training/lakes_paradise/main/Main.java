package by.training.lakes_paradise.main;

import by.training.lakes_paradise.db.ConnectionDB;
import by.training.lakes_paradise.db.dao.OrderDao;
import by.training.lakes_paradise.db.dao.ProfileDao;
import by.training.lakes_paradise.db.entity.*;
import by.training.lakes_paradise.db.mysql.*;
import by.training.lakes_paradise.exception.PersistentException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {
    public static void main(final String[] args) throws PersistentException, ParseException {

        try {
            Connection connection = ConnectionDB.getConnection();
            Statement statement = connection.createStatement();
            BufferedImage image = ImageIO.read(new File("www/img/1.2_farmstead.jpg"));
            Blob blob = connection.createBlob();
            try(OutputStream outputStream = blob.setBinaryStream(1)) {
                ImageIO.write(image, "jpg", outputStream);
            }

            ImageDaoRealization imageDaoRealization = new ImageDaoRealization();
            Image image1 = new Image();
            image1.setImage(blob);
            //image1.setIdHome(2);

            imageDaoRealization.create(image1);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

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
//        ReviewDaoRealization reviewDaoRealization = new ReviewDaoRealization();
//        for(var review : reviewDaoRealization.readReviewsByHomeId(1)) {
//            System.out.println(review);
//        }
    }
}
