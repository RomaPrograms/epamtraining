package by.training.lakes_paradise.db.entity;

import java.sql.Blob;

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
    private int homeId;

    /**
     * Gets the value of image property.
     *
     * @return value of image property
     */
    public Blob getImage() {
        return image;
    }

    /**
     * Sets the value of image property.
     *
     * @param homesteadImage - value of image property
     */
    public void setImage(final Blob homesteadImage) {
        this.image = homesteadImage;
    }

    /**
     * Gets the value of homeId property.
     *
     * @return value of homeId property
     */
    public Integer getHomeId() {
        return homeId;
    }

    /**
     * Sets the value of homeId property.
     *
     * @param imageHomeId - value of homeId property
     */
    public void setHomeId(final Integer imageHomeId) {
        this.homeId = imageHomeId;
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
                + ", \nhomeId=" + homeId
                + "\n}";
    }
}
