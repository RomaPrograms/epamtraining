package by.training.lakes_paradise.db.entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

/**
 * Class which describes image.
 */
public class Image extends Entity {
    /**
     * Image of homestead.
     */
    private Blob image;

    /**
     * Id of homestead which belongs image.
     */
    private Homestead homestead;

    /**
     * Gets the value of image property.
     *
     * @return value of image property
     */
    public Blob getImage() {
        return image;
    }

    public File getFileImage() {
        BufferedImage image1;
        File outputFile = null;

        try {
            image1 = ImageIO.read(image.getBinaryStream());
            outputFile = new File(this.getId() + ".png");
            ImageIO.write(image1, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return outputFile;
    }

    /*
     * Sets the value of image property.
     *
     * @param homesteadImage - value of image property
     */
    public void setImage(final Blob homesteadImage) {
        this.image = homesteadImage;
    }

    /**
     * Gets the value of homestead property.
     *
     * @return value of homestead property
     */
    public Homestead getHomestead() {
        return homestead;
    }

    /**
     * Sets the value of homestead property.
     *
     * @param imageHomeId - value of homestead property
     */
    public void setHomestead(final Homestead imageHomeId) {
        this.homestead = imageHomeId;
    }

    /**
     * Returns description of image in String.
     *
     * @return description of image in String
     */
    @Override
    public String toString() {
        return "Image{"
                + "\nimage=" + image
                + ", \nhomestead=" + homestead
                + "\n}";
    }
}
