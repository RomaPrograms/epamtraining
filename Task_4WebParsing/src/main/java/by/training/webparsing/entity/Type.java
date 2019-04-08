package by.training.webparsing.entity;

import java.util.Objects;

/**
 * Class that contains additional information about device.
 */
public class Type {
    /**
     * Shows does device have a cooler.
     */
    private boolean cooler;
    /**
     * Shows is device critical.
     */
    private boolean critical;
    /**
     * Power usage of device.
     */
    private double powerUsage;
    /**
     * Group of complects of device
     */
    private String groupOfComplects;
    /**
     * Ports of device.
     */
    private Port port;

    /**
     * No-argument constructor.
     */
    public Type() {
    }

    /**
     * Five-argument constructor.
     *
     * @param dCooler           - does cooler exist in this device
     * @param dCritical         - is device critical
     * @param dPowerUsage       - power usage of device
     * @param dGroupOfComplects - group of complects of device
     * @param dPort             - port of device
     */
    public Type(final boolean dCooler, final boolean dCritical,
                final double dPowerUsage, final String dGroupOfComplects,
                final Port dPort) {
        this.cooler = dCooler;
        this.critical = dCritical;
        this.powerUsage = dPowerUsage;
        this.groupOfComplects = dGroupOfComplects;
        this.port = dPort;
    }

    /**
     * Gets the value of port property.
     *
     * @return port of device
     */
    public Port getPort() {
        return port;
    }

    /**
     * Sets the value of port property.
     *
     * @param dPort - port of device
     */
    public void setPort(final Port dPort) {
        this.port = dPort;
    }

    /**
     * Returns {@code true} if this device has a cooler.
     *
     * @return {@code true} if this device has a cooler
     */
    public boolean isCooler() {
        return cooler;
    }

    /**
     * Sets the value of cooler property.
     *
     * @param dCooler - {@code true} if this device has a cooler
     */
    public void setCooler(final boolean dCooler) {
        this.cooler = dCooler;
    }

    /**
     * Returns {@code true} if this device is critical for computer.
     *
     * @return {@code true} if this device is critical for computer
     */
    public boolean isCritical() {
        return critical;
    }

    /**
     * Sets the value of critical property.
     *
     * @param dCritical - {@code true} if this device is critical for computer
     */
    public void setCritical(final boolean dCritical) {
        this.critical = dCritical;
    }

    /**
     * Gets the value of powerUsage property.
     *
     * @return power usage of device
     */
    public double getPowerUsage() {
        return powerUsage;
    }

    /**
     * Sets the value of powerUsage property.
     *
     * @param dPowerUsage - power Usage of device
     */
    public void setPowerUsage(final double dPowerUsage) {
        this.powerUsage = dPowerUsage;
    }

    /**
     * Gets the value of groupOfComplects property.
     *
     * @return group of complects of device
     */
    public String getGroupOfComplects() {
        return groupOfComplects;
    }

    /**
     * Sets the value of groupOfComplects property.
     *
     * @param dGroupOfComplects - group of complects of device
     */
    public void setGroupOfComplects(final String dGroupOfComplects) {
        this.groupOfComplects = dGroupOfComplects;
    }

    /**
     * Compares this type to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null} and is a {@code
     * Type} object that has the same properties values as this
     * object.
     *
     * @param o The object to compare this {@code Type} against
     * @return {@code true} if the given object represents a
     * {@code Type} equivalent to this device, {@code false} otherwise
     */
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof Type)) return false;
        Type type = (Type) o;
        return isCooler() == type.isCooler() &&
                isCritical() == type.isCritical() &&
                Double.compare(type.getPowerUsage(), getPowerUsage()) == 0 &&
                Objects.equals(getGroupOfComplects(), type.getGroupOfComplects()) &&
                getPort() == type.getPort();
    }

    /**
     * Returns a hash code for this device.
     *
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(isCooler(), isCritical(), getPowerUsage(), getGroupOfComplects(), getPort());
    }

    /**
     * Returns a string representation of the {@code Type}.
     *
     * @return string representation of the Type
     */
    @Override
    public String toString() {
        return "cooler=" + cooler +
                ", \ncritical=" + critical +
                ", \nport=" + port.toString() +
                ", \npowerUsage=" + powerUsage +
                ", \ngroupOfComplects='" + groupOfComplects + '\'';
    }
}
