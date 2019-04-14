package by.training.lakes_paradise.db.entity;

/**
 * Class which describes profile.
 */
public class Profile extends Entity{
    private String login;
    private String password;
    private Integer orders;

    public String getLogin() {
        return login;
    }

    public void setLogin(final String profileLogin) {
        this.login = profileLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String profilePassword) {
        this.password = profilePassword;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(final Integer profileOrders) {
        this.orders = profileOrders;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "\nlogin='" + login + '\'' +
                ", \npassword='" + password + '\'' +
                ", \norders=" + orders +
                "\n}";
    }
}
