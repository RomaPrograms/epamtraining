package by.training.lakes_paradise.db.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
     * Id of owner who belongs homestead.
     */
    private User owner;

    /**
     * List with images of homestead.
     */
    private List<Image> images = new ArrayList<>();

    /**
     * List with reviews about homestead.
     */
    private List<Review> reviews = new ArrayList<>();

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
     * Gets the value of images property.
     *
     * @return value of images property.
     */
    public List<Image> getImages() {
        return images;
    }

    /**
     * Sets the value of images property.
     *
     * @param homesteadImages - value of images property.
     */
    public void setImages(final List<Image> homesteadImages) {
        this.images = homesteadImages;
    }

    /**
     * Gets the value of reviews property.
     *
     * @return value of reviews property.
     */
    public List<Review> getReviews() {
        return reviews;
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
    public int getPeopleNumber() {
        return peopleNumber;
    }

    /**
     * Sets the value of peopleNumber property.
     *
     * @param maxPeopleNumber - value of peopleNumber property.
     */
    public void setPeopleNumber(final int maxPeopleNumber) {
        this.peopleNumber = maxPeopleNumber;
    }

    /**
     * Gets the value of owner property.
     *
     * @return value of owner property.
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Sets the value of owner property.
     *
     * @param homesteadOwnerId - value of owner property.
     */
    public void setOwner(final User homesteadOwnerId) {
        this.owner = homesteadOwnerId;
    }

    /**
     * Checks equality of homesteads by matching properties.
     *
     * @param  o -
     *        The object to compare this {@code Homestead} against
     *
     * @return  {@code true} if the given object equivalent to this object,
     * {@code false} otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Homestead)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Homestead homestead = (Homestead) o;
        return getPeopleNumber() == homestead.getPeopleNumber()
                && Objects.equals(getTitle(), homestead.getTitle())
                && Objects.equals(getPrice(), homestead.getPrice())
                && Objects.equals(getDescription(), homestead.getDescription())
                && Objects.equals(getOwner(), homestead.getOwner())
                && Objects.equals(getImages(), homestead.getImages())
                && Objects.equals(getReviews(), homestead.getReviews());
    }

    /**
     * Calculates unique code for every homestead.
     *
     * @return unique code of homestead.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getTitle(), getPrice(),
                getDescription(), getPeopleNumber(), getOwner(), getImages(),
                getReviews());
    }
}
