package by.training.lakes_paradise.main;

import by.training.lakes_paradise.db.ConnectionDB;
import by.training.lakes_paradise.db.entity.Homestead;
import by.training.lakes_paradise.db.entity.Image;
import by.training.lakes_paradise.db.entity.Profile;
import by.training.lakes_paradise.db.entity.Role;
import by.training.lakes_paradise.db.mysql.*;
import by.training.lakes_paradise.exception.PersistentException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Main {
    public static void main(final String[] args) throws PersistentException {

        /*try {
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
        }*/


    }
}
