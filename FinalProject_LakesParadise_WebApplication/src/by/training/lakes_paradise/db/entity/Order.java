package by.training.lakes_paradise.db.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    private Date startRenting;
    /**
     * Date of end renting.
     */
    private Date endRenting;

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
    public Date getStartRenting() {
        return startRenting;
    }

    /**
     * Gets the value of startRenting property in string by special pattern.
     *
     * @return value of startRenting property.
     */
    public String getStartRentingByPattern() {
        SimpleDateFormat formatForDateNow
                = new SimpleDateFormat("E dd.MM.yyyy");

        return formatForDateNow.format(startRenting);
    }

    /**
     * Sets the value of startRenting property.
     *
     * @param orderStartRenting - value of startRenting property.
     */
    public void setStartRenting(final Date orderStartRenting) {
        this.startRenting = orderStartRenting;
    }

    /**
     * Gets the value of endRenting property.
     *
     * @return value of endRenting property.
     */
    public Date getEndRenting() {
        return endRenting;
    }

    /**
     * Gets the value of endRenting property in string by special pattern.
     *
     * @return value of endRenting property.
     */
    public String getEndRentingByPattern() {
        SimpleDateFormat formatForDateNow
                = new SimpleDateFormat("E dd.MM.yyyy");

        return formatForDateNow.format(endRenting);
    }

    /**
     * Sets the value of endRenting property.
     *
     * @param orderEndRenting - value of endRenting property.
     */
    public void setEndRenting(final Date orderEndRenting) {
        this.endRenting = orderEndRenting;
    }
}
