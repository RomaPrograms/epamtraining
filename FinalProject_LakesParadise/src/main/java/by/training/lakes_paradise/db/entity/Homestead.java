package by.training.lakes_paradise.db.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which describes homestead.
 */
public class Homestead extends Entity {
    private String title;
    private BigDecimal price;
    private String description;
    private int peopleNumber;
    private double rating;
    private Order currentOrder;
    private int ownerId;
    private List<Order> orders = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(final String homeTitle) {
        this.title = homeTitle;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal homePrice) {
        this.price = homePrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String homeDescription) {
        this.description = homeDescription;
    }

    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    public void setPeopleNumber(Integer peopleNumber) {
        this.peopleNumber = peopleNumber;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(final Double homeRating) {
        this.rating = homeRating;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Homestead{" +
                "\ntitle='" + title + '\'' +
                ", \nprice=" + price +
                ", \ndescription='" + description + '\'' +
                ", \npeopleNumber=" + peopleNumber +
                ", \nrating=" + rating +
                ", \ncurrentOrder=" + currentOrder +
                ", \nownerId=" + ownerId +
                ", \norders=" + orders +
                "\n}";
    }
}
