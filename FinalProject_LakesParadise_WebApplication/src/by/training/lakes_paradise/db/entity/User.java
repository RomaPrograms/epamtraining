package by.training.lakes_paradise.db.entity;

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
     * Sets the description of user in String.
     *
     * @return description of user in String
     */
    @Override
    public String toString() {
        return "User{"
                + "\nname='" + name + '\''
                + ", \nsurname='" + surname + '\''
                + ", \nphone='" + phone + '\''
                + "\n}";
    }
}
