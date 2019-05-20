package by.training.lakes_paradise.db.entity;

import java.util.Objects;

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
     * Checks equality of profiles by matching properties.
     *
     * @param  o -
     *        The object to compare this {@code Profile} against
     *
     * @return  {@code true} if the given object equivalent to this object,
     * {@code false} otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Profile)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Profile profile = (Profile) o;
        return Objects.equals(getLogin(), profile.getLogin())
                && Objects.equals(getPassword(), profile.getPassword())
                && getRole() == profile.getRole();
    }

    /**
     * Calculates unique code for every profile.
     *
     * @return unique code of profile.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getLogin(), getPassword(),
                getRole());
    }
}
