package by.training.lakes_paradise.db.entity;

/**
 * Class which describes orders.
 */
public class Order extends Entity {
    private Homestead homestead;
    private Profile profile;
    private Long startRenting;
    private Long endRenting;
    private Boolean isPaid;

    public Homestead getHomestead() {
        return homestead;
    }

    public void setHomestead(final Homestead orderHomestead) {
        this.homestead = orderHomestead;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(final Profile orderProfile) {
        this.profile = orderProfile;
    }

    public Long getStartRenting() {
        return startRenting;
    }

    public void setStartRenting(final Long orderStartRenting) {
        this.startRenting = orderStartRenting;
    }

    public Long getEndRenting() {
        return endRenting;
    }

    public void setEndRenting(final Long orderEndRenting) {
        this.endRenting = orderEndRenting;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(final Boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "\nhomestead=" + homestead +
                ", \nprofile=" + profile +
                ", \nstartRenting=" + startRenting +
                ", \nendRenting=" + endRenting +
                ", \nisPaid=" + isPaid +
                "\n}";
    }
}
