package by.training.lakes_paradise.db.entity;

import java.sql.Blob;

public class Image extends Entity{
    private Blob image;
    private Integer homeId;

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public Integer getHomeId() {
        return homeId;
    }

    public void setHomeId(Integer homeId) {
        this.homeId = homeId;
    }

    @Override
    public String toString() {
        return "Image{" +
                "\nimage=" + image +
                ", \nhomeId=" + homeId +
                "\n}";
    }
}
