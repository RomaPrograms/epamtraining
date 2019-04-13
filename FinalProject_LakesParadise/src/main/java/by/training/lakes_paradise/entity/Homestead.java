package by.training.lakes_paradise.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Homestead extends Entity{
    private String title;
    private Boolean status;
    private BigDecimal price;
    private String description;
    private Double rating;
    private Order currentOrder;
    private Owner owner;
    private List<Order> orders = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order currentOrder) {
        this.currentOrder = currentOrder;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Homestead{" +
                "\ntitle='" + title + '\'' +
                ", \nstatus=" + status +
                ", \nprice=" + price +
                ", \ndescription='" + description + '\'' +
                ", \nrating=" + rating +
                ", \ncurrentOrder=" + currentOrder +
                ", \nowner=" + owner +
                ", \norders=" + orders +
                "\n}";
    }
}
