package by.training.lakes_paradise.db.entity;

/**
 * Class which describes orders.
 */
public class Order extends Entity {
    private int homesteadId;
    private int profileId;
    private long startRenting;
    private long endRenting;
    private boolean isPaid;

    public int getHomesteadId() {
        return homesteadId;
    }

    public void setHomesteadId(int homesteadId) {
        this.homesteadId = homesteadId;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
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
                "\nhomesteadId=" + homesteadId +
                ", \nprofileId=" + profileId +
                ", \nstartRenting=" + startRenting +
                ", \nendRenting=" + endRenting +
                ", \nisPaid=" + isPaid +
                "\n}";
    }
}
