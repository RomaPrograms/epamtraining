package by.training.lakes_paradise.db.entity;

/**
 * Class which describes user.
 */
public class User extends Entity {
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
    private String phone;
    /**
     * Town of user.
     */
    private String town;

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
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the value of property phone.
     *
     * @param userPhone - value of property phone
     */
    public void setPhone(final String userPhone) {
        this.phone = userPhone;
    }

    /**
     * Gets the value of property town.
     *
     * @return value of property town.
     */
    public String getTown() {
        return town;
    }

    /**
     * Sets the value of property town.
     *
     * @param userTown - value of property town
     */
    public void setTown(final String userTown) {
        this.town = userTown;
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
                + ", \ntown='" + town + '\''
                + "\n}";
    }
}
