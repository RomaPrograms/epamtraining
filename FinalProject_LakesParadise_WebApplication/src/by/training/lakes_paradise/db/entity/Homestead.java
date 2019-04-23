package by.training.lakes_paradise.db.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Class which describes homestead.
 */
public class Homestead extends Entity {
    /**
     * Title of homestead.
     */
    private String title;
    /**
     * Price of homestead.
     */
    private BigDecimal price;
    /**
     * Description of homestead.
     */
    private String description;
    /**
     * Max number of people in homestead.
     */
    private int peopleNumber;
    /**
     * Rating of homestead.
     */
    private double rating;
    private Order currentOrder;
    /**
     * Id of owner who belongs homestead.
     */
    private int ownerId;
    private List<Order> orders = new ArrayList<>();

    /**
     * Gets the value of title property.
     *
     * @return value of title property.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of title property.
     *
     * @param homeTitle - value of title property.
     */
    public void setTitle(final String homeTitle) {
        this.title = homeTitle;
    }

    /**
     * Gets the value of price property.
     *
     * @return value of price property.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets the value of price property.
     *
     * @param homePrice - value of price property.
     */
    public void setPrice(final BigDecimal homePrice) {
        this.price = homePrice;
    }

    /**
     * Gets the value of description property.
     *
     * @return value of description property.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of description property.
     *
     * @param homeDescription - value of description property.
     */
    public void setDescription(final String homeDescription) {
        this.description = homeDescription;
    }

    /**
     * Gets the value of peopleNumber property.
     *
     * @return value of peopleNumber property.
     */
    public Integer getPeopleNumber() {
        return peopleNumber;
    }

    /**
     * Sets the value of peopleNumber property.
     *
     * @param maxPeopleNumber - value of peopleNumber property.
     */
    public void setPeopleNumber(Integer maxPeopleNumber) {
        this.peopleNumber = maxPeopleNumber;
    }

    /**
     * Gets the value of rating property.
     *
     * @return value of rating property.
     */
    public Double getRating() {
        return rating;
    }

    /**
     * Sets the value of rating property.
     *
     * @param homeRating - value of rating property.
     */
    public void setRating(final Double homeRating) {
        this.rating = homeRating;
    }

    /**
     * Gets the value of ownerId property.
     *
     * @return value of ownerId property.
     */
    public int getOwnerId() {
        return ownerId;
    }

    /**
     * Sets the value of ownerId property.
     *
     * @param homesteadOwnerId - value of ownerId property.
     */
    public void setOwnerId(final int homesteadOwnerId) {
        this.ownerId = homesteadOwnerId;
    }

    /**
     * Returns description of homestead in String.
     *
     * @return description of homestead in String.
     */
    @Override
    public String toString() {
        return "Homestead{"
                + "\ntitle='" + title + '\''
                + ", \nprice=" + price
                + ", \ndescription='" + description + '\''
                + ", \npeopleNumber=" + peopleNumber
                + ", \nrating=" + rating
                + ", \ncurrentOrder=" + currentOrder
                + ", \nownerId=" + ownerId
                + ", \norders=" + orders
                + "\n}";
    }
}
