package by.training.webparsing.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

/**
 * Entity class that describes device.
 */
public abstract class Device {
    /**
     * Name of device.
     */
    private String name;
    /**
     * Origin of device.
     */
    private String origin;
    /**
     * Price of device.
     */
    private double price;
    /**
     * Type of device.
     */
    private Type type;
    /**
     * Date of delivery of device.
     */
    private Calendar dateOfDelivery;

    /**
     * No-argument constructor.
     */
    public Device() {
        type = new Type();
    }

    /**
     * Constructor with sixth arguments.
     *
     * @param dName           - name of device
     * @param dOrigin         - origin of device
     * @param dPrice          - price of device
     * @param dType           - type of device
     * @param dDateOfDelivery - date of delivery of device
     */
    public Device(final String dName, final String dOrigin, final double dPrice,
                  final Type dType, final Calendar dDateOfDelivery) {
        this.name = dName;
        this.origin = dOrigin;
        this.price = dPrice;
        this.type = dType;
        this.dateOfDelivery = dDateOfDelivery;
    }

    /**
     * Gets the value of the name property.
     *
     * @return name of device
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param dName - name of device
     */
    public void setName(final String dName) {
        this.name = dName;
    }

    /**
     * Gets the value of the origin property.
     *
     * @return origin of device
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Sets the value of the origin property.
     *
     * @param dOrigin - origin of device
     */
    public void setOrigin(final String dOrigin) {
        this.origin = dOrigin;
    }

    /**
     * Gets the value of the price property.
     *
     * @return price of device
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the value of price property.
     *
     * @param dPrice - price of device
     */
    public void setPrice(final double dPrice) {
        this.price = dPrice;
    }

    /**
     * Gets the value of type property.
     *
     * @return type of device
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the value of Type property.
     *
     * @param dType - type of device
     */
    public void setType(final Type dType) {
        this.type = dType;
    }

    /**
     * Gets the value  of dateOfDelivery property.
     *
     * @return dateOfDelivery of device
     */
    public Calendar getDateOfDelivery() {
        return dateOfDelivery;
    }

    /**
     * Sets the value of dateOfDelivery property.
     *
     * @param dDateOfDelivery - date of delivery of device
     */
    public void setDateOfDelivery(final Calendar dDateOfDelivery) {
        this.dateOfDelivery = dDateOfDelivery;
    }

    /**
     * Compares this device to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@code
     * Device} object that has the same properties values as this
     * object.
     *
     * @param o The object to compare this {@code Device} against
     * @return {@code true} if the given object represents a {@code Device}
     * equivalent to this device, {@code false} otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Device)) {
            return false;
        }
        Device that = (Device) o;
        return Double.compare(that.getPrice(), getPrice()) == 0
                && Objects.equals(getName(), that.getName())
                && Objects.equals(getOrigin(), that.getOrigin())
                && Objects.equals(getType(), that.getType())
                && Objects.equals(getDateOfDelivery(),
                that.getDateOfDelivery());
    }

    /**
     * Returns a hash code for this device.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getName(), getOrigin(), getPrice(), getType(),
                getDateOfDelivery());
    }

    /**
     * Returns a string representation of the {@code Device}.
     *
     * @return string representation of the Device
     */
    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat
                = new SimpleDateFormat("dd MMM yyy");

        return "name='" + name + '\''
                + ", \norigin='" + origin + '\''
                + ", \nprice=" + price + ", \n" + type.toString()
                + ", \ndateOfDelivery="
                + simpleDateFormat.format(dateOfDelivery.getTime());
    }
}
