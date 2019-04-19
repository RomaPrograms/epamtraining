package by.training.lakes_paradise.db.entity;

/**
 * Class which describes profile.
 */
public class Profile extends Entity {
    /**
     * Login of profile.
     */
    private String login;
    /**
     * Password of profile.
     */
    private String password;
    /**
     * Role of profile.
     */
    private Role role;

    /**
     * Gets the value of property login.
     *
     * @return value of property login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets the value of property login.
     *
     * @param profileLogin - value of property login
     */
    public void setLogin(final String profileLogin) {
        this.login = profileLogin;
    }

    /**
     * Gets the the value of property password.
     *
     * @return value of property password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of property password.
     *
     * @param profilePassword - value of property password
     */
    public void setPassword(final String profilePassword) {
        this.password = profilePassword;
    }

    /**
     * Gets the value of property role.
     *
     * @return value of property role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the value of property role.
     *
     * @param profileRole - value of property role
     */
    public void setRole(final Role profileRole) {
        this.role = profileRole;
    }

    /**
     * Returns description of profile in String.
     *
     * @return description of profile in String
     */
    @Override
    public String toString() {
        return "Profile{"
                + "\nlogin='" + login + '\''
                + ", \npassword='" + password + '\''
                + ", \nrole=" + role
                + "\n}";
    }
}
