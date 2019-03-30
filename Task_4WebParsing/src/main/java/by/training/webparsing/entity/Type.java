package by.training.webparsing.entity;

import java.util.Objects;

public class Type {
    private boolean cooler;
    private boolean critical;
    private double powerUsage;
    private String groupOfComplects;
    private Port port;

    public Type() { }

    public Type(boolean cooler, boolean critical, double powerUsage,
                String groupOfComplects, Port port) {
        this.cooler = cooler;
        this.critical = critical;
        this.powerUsage = powerUsage;
        this.groupOfComplects = groupOfComplects;
        this.port = port;
    }

    public Port getPort() {
        return port;
    }

    public void setPort(Port port) {
        this.port = port;
    }

    public boolean isCooler() {
        return cooler;
    }

    public void setCooler(boolean cooler) {
        this.cooler = cooler;
    }

    public boolean isCritical() {
        return critical;
    }

    public void setCritical(boolean critical) {
        this.critical = critical;
    }

    public double getPowerUsage() {
        return powerUsage;
    }

    public void setPowerUsage(double powerUsage) {
        this.powerUsage = powerUsage;
    }

    public String getGroupOfComplects() {
        return groupOfComplects;
    }

    public void setGroupOfComplects(String groupOfComplects) {
        this.groupOfComplects = groupOfComplects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Type)) return false;
        Type type = (Type) o;
        return isCooler() == type.isCooler() &&
                isCritical() == type.isCritical() &&
                Double.compare(type.getPowerUsage(), getPowerUsage()) == 0 &&
                Objects.equals(getGroupOfComplects(), type.getGroupOfComplects()) &&
                getPort() == type.getPort();
    }

    @Override
    public int hashCode() {
        return Objects.hash(isCooler(), isCritical(), getPowerUsage(), getGroupOfComplects(), getPort());
    }

    @Override
    public String toString() {
        return "cooler=" + cooler +
                ", \ncritical=" + critical +
                ", \nport=" + port.toString() +
                ", \npowerUsage=" + powerUsage +
                ", \ngroupOfComplects='" + groupOfComplects + '\'';
    }
}
