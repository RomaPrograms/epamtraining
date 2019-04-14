package by.training.lakes_paradise.db.entity;

/**
 * Class which describes owners.
 */
public class Owner extends Entity {
    private String name;
    private String surname;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(final String ownerName) {
        this.name = ownerName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String ownerSurname) {
        this.surname = ownerSurname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(final String ownerPhone) {
        this.phone = ownerPhone;
    }
}
