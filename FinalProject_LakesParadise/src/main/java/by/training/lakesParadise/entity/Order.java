package by.training.lakesParadise.entity;

import java.util.Date;

public class Order extends Entity {
    private Homestead homestead;
    private User user;
    private Date startRenting;
    private Date endRenting;
    private Boolean isRented;
    private Integer numberOfPeople;

    public Homestead getHomestead() {
        return homestead;
    }

    public void setHomestead(Homestead homestead) {
        this.homestead = homestead;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getStartRenting() {
        return startRenting;
    }

    public void setStartRenting(Date startRenting) {
        this.startRenting = startRenting;
    }

    public Date getEndRenting() {
        return endRenting;
    }

    public void setEndRenting(Date endRenting) {
        this.endRenting = endRenting;
    }

    public Boolean getRented() {
        return isRented;
    }

    public void setRented(Boolean rented) {
        isRented = rented;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }
}
