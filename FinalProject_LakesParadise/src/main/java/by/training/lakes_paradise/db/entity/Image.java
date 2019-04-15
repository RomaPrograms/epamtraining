package by.training.lakes_paradise.db.entity;

import java.sql.Blob;

public class Image extends Entity{
    private Blob image;
    private Integer idHome;

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public Integer getIdHome() {
        return idHome;
    }

    public void setIdHome(Integer idHome) {
        this.idHome = idHome;
    }
}
