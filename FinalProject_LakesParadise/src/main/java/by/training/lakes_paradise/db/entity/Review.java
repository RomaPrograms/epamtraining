package by.training.lakes_paradise.db.entity;

public class Review extends Entity {
    private String text;
    private String userName;
    private long dateOfComment;
    private int homeId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long getDateOfComment() {
        return dateOfComment;
    }

    public void setDateOfComment(Long dateOfComment) {
        this.dateOfComment = dateOfComment;
    }

    public Integer getHomeId() {
        return homeId;
    }

    public void setHomeId(Integer homeId) {
        this.homeId = homeId;
    }

    @Override
    public String toString() {
        return "Review{" +
                "\ntext='" + text + '\'' +
                ", \nuserName='" + userName + '\'' +
                ", \ndateOfComment=" + dateOfComment +
                ", \nhomeId=" + homeId +
                "\n}";
    }
}
