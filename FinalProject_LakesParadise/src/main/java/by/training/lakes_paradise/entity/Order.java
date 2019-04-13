package by.training.lakes_paradise.entity;

public class Order extends Entity {
    private Homestead homestead;
    private Profile profile;
    private Long startRenting;
    private Long endRenting;
    private Boolean isPaid;
    private Integer numberOfPeople;

    public Homestead getHomestead() {
        return homestead;
    }

    public void setHomestead(Homestead homestead) {
        this.homestead = homestead;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Long getStartRenting() {
        return startRenting;
    }

    public void setStartRenting(Long startRenting) {
        this.startRenting = startRenting;
    }

    public Long getEndRenting() {
        return endRenting;
    }

    public void setEndRenting(Long endRenting) {
        this.endRenting = endRenting;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Integer getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(Integer numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public String toString() {
        return "Order{" +
                "\nhomestead=" + homestead +
                ", \nprofile=" + profile +
                ", \nstartRenting=" + startRenting +
                ", \nendRenting=" + endRenting +
                ", \nisPaid=" + isPaid +
                ", \nnumberOfPeople=" + numberOfPeople +
                "\n}";
    }
}
