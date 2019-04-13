package by.training.lakes_paradise.entity;

public class User extends Entity{
    private String name;
    private String surname;
    private String phone;
    private String town;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
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
