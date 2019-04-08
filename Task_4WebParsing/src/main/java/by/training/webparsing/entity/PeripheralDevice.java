package by.training.webparsing.entity;

import java.util.Calendar;
import java.util.Objects;

/**
 * Entity class that describes peripheral device.
 */
public class PeripheralDevice extends Device {
    /**
     * Connection fo device.
     */
    private Connection connection;

    /**
     * No-argument constructor.
     */
    public PeripheralDevice() {
    }

    /**
     * One-argument constructor.
     *
     * @param dConnection - connection of our device
     */
    public PeripheralDevice(final Connection dConnection) {
        this.connection = dConnection;
    }

    /**
     * Sixth-argument constructor.
     *
     * @param dName           - name of device
     * @param dOrigin         - origin of device
     * @param dPrice          - price of device
     * @param dType           - type of device
     * @param dDateOfDelivery - date of delivery of device
     * @param dConnection     - connection of device
     */
    public PeripheralDevice(final String dName, final String dOrigin,
                            final double dPrice, final Type dType,
                            final Calendar dDateOfDelivery,
                            final Connection dConnection) {
        super(dName, dOrigin, dPrice, dType, dDateOfDelivery);
        this.connection = dConnection;
    }

    /**
     * Gets the value of connection property.
     *
     * @return connection type of device
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * Sets the value of connection property.
     *
     * @param dConnection - connection type of device
     */
    public void setConnection(final Connection dConnection) {
        this.connection = dConnection;
    }

    /**
     * Compares this device to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@code
     * PeripheralDevice} object that has the same properties values as this
     * object.
     *
     * @param o The object to compare this {@code PeripheralDevice} against
     * @return {@code true} if the given object represents a
     * {@code PeripheralDevice} equivalent to this device,
     * {@code false} otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PeripheralDevice)) {
            return false;
        }
        PeripheralDevice that = (PeripheralDevice) o;
        return getConnection() == that.getConnection();
    }

    /**
     * Returns a hash code for this device.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getConnection());
    }

    /**
     * Returns a string representation of the {@code PeripheralDevice}.
     *
     * @return string representation of the PeripheralDevice
     */
    @Override
    public String toString() {
        return "PeripheralDevice{" + super.toString()
                + "\nconnection=" + connection.getValue()
                + "\n}";
    }
}
