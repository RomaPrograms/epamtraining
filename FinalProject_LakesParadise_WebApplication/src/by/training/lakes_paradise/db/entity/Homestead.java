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

    /**
     * Number of people which saved a comment.
     */
    private long numberOfVotedUsers;

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
     * Gets the value of numberOfVotedUsers property.
     *
     * @return value of numberOfVotedUsers property.
     */
    public long getNumberOfVotedUsers() {
        return numberOfVotedUsers;
    }

    /**
     * Sets the value of numberOfVotedUsers property.
     *
     * @param homesteadNumberOfVotedUsers - value of numberOfVotedUsers
     * property.
     */
    public void setNumberOfVotedUsers(final long homesteadNumberOfVotedUsers) {
        this.numberOfVotedUsers = homesteadNumberOfVotedUsers;
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
     * Gets the value of rating property.
     *
     * @return value of rating property.
     */
    public double getRating() {
        return rating;
    }

    /**
     * Sets the value of rating property.
     *
     * @param homeRating - value of rating property.
     */
    public void setRating(final double homeRating) {
        this.rating = homeRating;
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
                + ", \nowner=" + owner
                + "\n}";
    }
}
