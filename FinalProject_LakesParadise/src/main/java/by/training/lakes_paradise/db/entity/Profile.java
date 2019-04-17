package by.training.lakes_paradise.db.entity;

/**
 * Class which describes profile.
 */
public class Profile extends Entity {
    private String login;
    private String password;
    private Role role;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "\nlogin='" + login + '\'' +
                ", \npassword='" + password + '\'' +
                ", \nrole=" + role +
                "\n}";
    }
}
