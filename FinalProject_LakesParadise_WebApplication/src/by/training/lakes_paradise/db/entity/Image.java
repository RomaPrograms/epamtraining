package by.training.lakes_paradise.db.entity;

import java.util.Objects;

/**
 * Class which describes image.
 */
public class Image extends Entity {

    /**
     * Path to image in project.
     */
    private String pathToImage;

    /**
     * Id of homestead which belongs image.
     */
    private int homesteadId;

    /**
     * Gets the value of pathToImage property.
     *
     * @return value of pathToImage property
     */
    public String getPathToImage() {
        return pathToImage;
    }

    /**
     * Sets the value of pathToImage property.
     *
     * @param newPathToImage - value of pathToImage property
     */
    public void setPathToImage(final String newPathToImage) {
        this.pathToImage = newPathToImage;
    }

    /**
     * Gets the value of homesteadId property.
     *
     * @return value of homesteadId property
     */
    public int getHomesteadId() {
        return homesteadId;
    }

    /**
     * Sets the value of homesteadId property.
     *
     * @param newHomesteadId - value of homestead property
     */
    public void setHomesteadId(final int newHomesteadId) {
        this.homesteadId = newHomesteadId;
    }

    /**
     * Checks equality of images by matching properties.
     *
     * @param  o -
     *        The object to compare this {@code Image} against
     *
     * @return  {@code true} if the given object equivalent to this object,
     * {@code false} otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Image)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Image image = (Image) o;
        return getHomesteadId() == image.getHomesteadId()
                && Objects.equals(getPathToImage(), image.getPathToImage());
    }

    /**
     * Calculates unique code for every image.
     *
     * @return unique code of image.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getPathToImage(),
                getHomesteadId());
    }
}
