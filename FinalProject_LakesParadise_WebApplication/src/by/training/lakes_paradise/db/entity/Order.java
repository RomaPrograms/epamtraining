package by.training.lakes_paradise.db.entity;

/**
 * Class which describes order.
 */
public class Order extends Entity {
    /**
     * Id of homestead which is ordered.
     */
    private int homesteadId;
    /**
     * Id of profile which ordered homestead.
     */
    private int profileId;
    /**
     * Date of start renting.
     */
    private long startRenting;
    /**
     * Date of end renting.
     */
    private long endRenting;
    /**
     * Payment state.
     */
    private boolean isPaid;

    /**
     * Gets the value of homesteadId property.
     *
     * @return value of homesteadId property.
     */
    public int getHomesteadId() {
        return homesteadId;
    }

    /**
     * Sets the value of homesteadId property.
     *
     * @param orderedHomesteadId - value of homesteadId property.
     */
    public void setHomesteadId(final int orderedHomesteadId) {
        this.homesteadId = orderedHomesteadId;
    }

    /**
     * Gets the value of profileId property.
     *
     * @return value of profileId property.
     */
    public int getProfileId() {
        return profileId;
    }

    /**
     * Sets the value of profileId property.
     *
     * @param clientId - value of profileId property.
     */
    public void setProfileId(final int clientId) {
        this.profileId = clientId;
    }

    /**
     * Gets the value of startRenting property.
     *
     * @return value of startRenting property.
     */
    public Long getStartRenting() {
        return startRenting;
    }

    /**
     * Sets the value of startRenting property.
     *
     * @param orderStartRenting - value of startRenting property.
     */
    public void setStartRenting(final Long orderStartRenting) {
        this.startRenting = orderStartRenting;
    }

    /**
     * Gets the value of endRenting property.
     *
     * @return value of endRenting property.
     */
    public Long getEndRenting() {
        return endRenting;
    }

    /**
     * Sets the value of endRenting property.
     *
     * @param orderEndRenting - value of endRenting property.
     */
    public void setEndRenting(final Long orderEndRenting) {
        this.endRenting = orderEndRenting;
    }

    /**
     * Gets the value of isPaid property.
     *
     * @return value of isPaid property.
     */
    public Boolean getPaid() {
        return isPaid;
    }

    /**
     * Sets the value of isPaid property.
     *
     * @param paid - value of isPaid property.
     */
    public void setPaid(final Boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        return "Order{"
                + "\nhomesteadId=" + homesteadId
                + ", \nprofileId=" + profileId
                + ", \nstartRenting=" + startRenting
                + ", \nendRenting=" + endRenting
                + ", \nisPaid=" + isPaid
                + "\n}";
    }
}
