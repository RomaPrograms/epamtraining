package by.training.lakes_paradise.db.entity;

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
     * @param pathToImage - value of pathToImage property
     */
    public void setPathToImage(String pathToImage) {
        this.pathToImage = pathToImage;
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
     * @param homesteadId - value of homestead property
     */
    public void setHomesteadId(int homesteadId) {
        this.homesteadId = homesteadId;
    }
}
