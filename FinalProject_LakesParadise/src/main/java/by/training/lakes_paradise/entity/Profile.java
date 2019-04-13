package by.training.lakes_paradise.entity;

public class Profile extends Entity{
    private String login;
    private String password;
    private Integer orders;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getOrders() {
        return orders;
    }

    public void setOrders(Integer orders) {
        this.orders = orders;
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
