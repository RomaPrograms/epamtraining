package by.training.webparsing.entity;

import java.util.Calendar;
import java.util.Objects;

/**
 * Entity class that describes inner device.
 */
public class InnerDevice extends Device {
    /**
     * Version of device.
     */
    private String version;

    /**
     * No-argument constructor.
     */
    public InnerDevice() {
    }

    /**
     * One argument constructor.
     *
     * @param dVersion - version of device
     */
    public InnerDevice(final String dVersion) {
        this.version = dVersion;
    }

    /**
     * Six arguments constructor.
     *
     * @param dName           - name of device
     * @param dOrigin         - origin of device
     * @param dPrice          - price of device
     * @param dType           - type of device
     * @param dDateOfDelivery -  date of delivery of device
     * @param dVersion        - version of device
     */
    public InnerDevice(final String dName, final String dOrigin,
                       final double dPrice, final Type dType,
                       final Calendar dDateOfDelivery, final String dVersion) {
        super(dName, dOrigin, dPrice, dType, dDateOfDelivery);
        this.version = dVersion;
    }

    /**
     * Gets the value of version property.
     *
     * @return version of device
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of version property.
     *
     * @param version - version of device
     */
    public void setVersion(final String version) {
        this.version = version;
    }

    /**
     * Compares this device to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@code
     * InnerDevice} object that has the same properties values as this
     * object.
     *
     * @param o The object to compare this {@code InnerDevice} against
     * @return {@code true} if the given object represents a
     * {@code InnerDevice} equivalent to this device, {@code false} otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof InnerDevice)) return false;
        if (!super.equals(o)) return false;
        InnerDevice that = (InnerDevice) o;
        return Objects.equals(getVersion(), that.getVersion());
    }

    /**
     * Returns a hash code for this device.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getVersion());
    }

    /**
     * Returns a string representation of the {@code InnerDevice}.
     *
     * @return string representation of the InnerDevice
     */
    @Override
    public String toString() {
        return "InnerDevice{" + super.toString() +
                "\nversion='" + version + '\'' +
                "\n}";
    }
}
