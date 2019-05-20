package by.training.lakes_paradise.db.entity;

import java.util.Objects;

/**
 * Class which describes user.
 */
public class User extends Profile {
    /**
     * Name of user.
     */
    private String name;
    /**
     * Surname of user.
     */
    private String surname;
    /**
     * Phone of user.
     */
    private long phone;

    /**
     * Gets the value of property name.
     *
     * @return value of property name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of property userName.
     *
     * @param userName - value of property userName
     */
    public void setName(final String userName) {
        this.name = userName;
    }

    /**
     * Gets the value of property surname.
     *
     * @return value of property surname.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets the value of property surname.
     *
     * @param userSurname - value of property surname
     */
    public void setSurname(final String userSurname) {
        this.surname = userSurname;
    }

    /**
     * Gets the value of property phone.
     *
     * @return value of property phone.
     */
    public long getPhone() {
        return phone;
    }

    /**
     * Sets the value of property phone.
     *
     * @param userPhone - value of property phone
     */
    public void setPhone(final long userPhone) {
        this.phone = userPhone;
    }

    /**
     * Checks equality of users by matching properties.
     *
     * @param  o -
     *        The object to compare this {@code User} against
     *
     * @return  {@code true} if the given object equivalent to this object,
     * {@code false} otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        User user = (User) o;
        return getPhone() == user.getPhone()
                && Objects.equals(getName(), user.getName())
                && Objects.equals(getSurname(), user.getSurname());
    }

    /**
     * Calculates unique code for every user.
     *
     * @return unique code of user.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getName(), getSurname(),
                getPhone());
    }
}
