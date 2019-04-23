package by.training.lakes_paradise.db.entity;

/**
 * Class which describes review.
 */
public class Review extends Entity {
    /**
     * Text of review.
     */
    private String text;
    /**
     * Name of user who wrote the comment.
     */
    private String userName;
    /**
     * Date when the comment was written.
     */
    private long dateOfComment;
    /**
     * Id of home which belongs the comment.
     */
    private int homeId;

    /**
     * Gets the value of text property.
     *
     * @return value of text property
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the value of text property.
     *
     * @param reviewText - value of text property
     */
    public void setText(final String reviewText) {
        this.text = reviewText;
    }

    /**
     * Gets the value of userName property.
     *
     * @return value of userName property
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of userName property.
     *
     * @param reviewUserName - value of userName property
     */
    public void setUserName(final String reviewUserName) {
        this.userName = reviewUserName;
    }

    /**
     * Gets the value of dateOfComment property.
     *
     * @return value of dateOfComment property
     */
    public Long getDateOfComment() {
        return dateOfComment;
    }

    /**
     * Sets the value of dateOfComment property.
     *
     * @param reviewDateOfComment - value of dateOfComment property
     */
    public void setDateOfComment(final Long reviewDateOfComment) {
        this.dateOfComment = reviewDateOfComment;
    }

    /**
     * Gets the value of homeId property.
     *
     * @return value of homeId property
     */
    public Integer getHomeId() {
        return homeId;
    }

    /**
     * Sets the value of homeId property.
     *
     * @param reviewHomeId - value of homeId property
     */
    public void setHomeId(final Integer reviewHomeId) {
        this.homeId = reviewHomeId;
    }

    /**
     * Returns description of review in String.
     *
     * @return description of review in String
     */
    @Override
    public String toString() {
        return "Review{"
                + "\ntext='" + text + '\''
                + ", \nuserName='" + userName + '\''
                + ", \ndateOfComment=" + dateOfComment
                + ", \nhomeId=" + homeId
                + "\n}";
    }
}
