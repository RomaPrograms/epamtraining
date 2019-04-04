package by.training.webparsing.entity;

import java.util.Calendar;
import java.util.Objects;

public abstract class Device {
    private String name;
    private String origin;
    private double price;
    private Type type;
    private Calendar dateOfDelivery;

    public Device() {
        type = new Type();
    }

    public Device(String name, String origin, double price, Type type,
                  Calendar dateOfDelivery) {
        this.name = name;
        this.origin = origin;
        this.price = price;
        this.type = type;
        this.dateOfDelivery = dateOfDelivery;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Calendar getDateOfDelivery() {
        return dateOfDelivery;
    }

    public void setDateOfDelivery(Calendar dateOfDelivery) {
        this.dateOfDelivery = dateOfDelivery;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Device)) return false;
        Device that = (Device) o;
        return Double.compare(that.getPrice(), getPrice()) == 0 &&
                Objects.equals(getName(), that.getName()) &&
                Objects.equals(getOrigin(), that.getOrigin()) &&
                Objects.equals(getType(), that.getType()) &&
                Objects.equals(getDateOfDelivery(), that.getDateOfDelivery());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getOrigin(), getPrice(), getType(),
                getDateOfDelivery());
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", \norigin='" + origin + '\'' +
                ", \nprice=" + price + ", \n" + type.toString() +
                ", \ndateOfDelivery=" + dateOfDelivery.get(Calendar.YEAR) + "-"
                + dateOfDelivery.get(Calendar.MONTH) + "-"
                + dateOfDelivery.get(Calendar.DAY_OF_MONTH);
    }
}
