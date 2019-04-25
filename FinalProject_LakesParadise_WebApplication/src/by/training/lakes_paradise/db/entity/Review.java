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
    private Homestead homestead;

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
    public long getDateOfComment() {
        return dateOfComment;
    }

    /**
     * Sets the value of dateOfComment property.
     *
     * @param reviewDateOfComment - value of dateOfComment property
     */
    public void setDateOfComment(final long reviewDateOfComment) {
        this.dateOfComment = reviewDateOfComment;
    }

    /**
     * Gets the value of homestead property.
     *
     * @return value of homestead property
     */
    public Homestead getHomestead() {
        return homestead;
    }

    /**
     * Sets the value of homestead property.
     *
     * @param reviewHomeId - value of homestead property
     */
    public void setHomestead(final Homestead reviewHomeId) {
        this.homestead = reviewHomeId;
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
                + ", \nhomestead=" + homestead
                + "\n}";
    }
}
