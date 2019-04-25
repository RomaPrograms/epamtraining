package by.training.lakes_paradise.db.entity;

/**
 * Class which describes order.
 */
public class Order extends Entity {
    /**
     * Id of homestead which is ordered.
     */
    private Homestead homestead;
    /**
     * Id of profile which ordered homestead.
     */
    private User user;
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
     * Gets the value of homestead property.
     *
     * @return value of homestead property.
     */
    public Homestead getHomestead() {
        return homestead;
    }

    /**
     * Sets the value of homestead property.
     *
     * @param orderedHomesteadId - value of homestead property.
     */
    public void setHomestead(final Homestead orderedHomesteadId) {
        this.homestead = orderedHomesteadId;
    }

    /**
     * Gets the value of user property.
     *
     * @return value of user property.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the value of user property.
     *
     * @param clientId - value of user property.
     */
    public void setUser(final User clientId) {
        this.user = clientId;
    }

    /**
     * Gets the value of startRenting property.
     *
     * @return value of startRenting property.
     */
    public long getStartRenting() {
        return startRenting;
    }

    /**
     * Sets the value of startRenting property.
     *
     * @param orderStartRenting - value of startRenting property.
     */
    public void setStartRenting(final long orderStartRenting) {
        this.startRenting = orderStartRenting;
    }

    /**
     * Gets the value of endRenting property.
     *
     * @return value of endRenting property.
     */
    public long getEndRenting() {
        return endRenting;
    }

    /**
     * Sets the value of endRenting property.
     *
     * @param orderEndRenting - value of endRenting property.
     */
    public void setEndRenting(final long orderEndRenting) {
        this.endRenting = orderEndRenting;
    }

    /**
     * Gets the value of isPaid property.
     *
     * @return value of isPaid property.
     */
    public boolean getPaid() {
        return isPaid;
    }

    /**
     * Sets the value of isPaid property.
     *
     * @param paid - value of isPaid property.
     */
    public void setPaid(final boolean paid) {
        isPaid = paid;
    }

    @Override
    public String toString() {
        return "Order{"
                + "\nhomestead=" + homestead
                + ", \nuser=" + user
                + ", \nstartRenting=" + startRenting
                + ", \nendRenting=" + endRenting
                + ", \nisPaid=" + isPaid
                + "\n}";
    }
}
