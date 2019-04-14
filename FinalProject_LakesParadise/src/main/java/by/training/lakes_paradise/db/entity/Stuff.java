package by.training.lakes_paradise.db.entity;

/**
 * Class which describes stuff.
 */
public class Stuff extends Entity{
    private String login;
    private String password;
    private Role role;

    public String getLogin() {
        return login;
    }

    public void setLogin(final String stuffLogin) {
        this.login = stuffLogin;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String stuffPassword) {
        this.password = stuffPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(final Role stuffRole) {
        this.role = stuffRole;
    }
}
