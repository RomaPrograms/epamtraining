package by.training.lakes_paradise.db.entity;

/**
 * Class which describes user.
 */
public class User extends Entity{
    private String name;
    private String surname;
    private String phone;
    private String town;

    public String getName() {
        return name;
    }

    public void setName(final String userName) {
        this.name = userName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String userSurname) {
        this.surname = userSurname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String userPhone) {
        this.phone = userPhone;
    }

    public String getTown() {
        return town;
    }

    public void setTown(final String userTown) {
        this.town = userTown;
    }

    @Override
    public String toString() {
        return "User{" +
                "\nname='" + name + '\'' +
                ", \nsurname='" + surname + '\'' +
                ", \nphone='" + phone + '\'' +
                ", \ntown='" + town + '\'' +
                "\n}";
    }
}
