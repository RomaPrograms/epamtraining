package by.training.webparsing.entity;

import java.util.Calendar;
import java.util.Objects;

public class InnerDevice extends DeviceType{
    private String version;

    public InnerDevice() { }

    public InnerDevice(String version) {
        this.version = version;
    }

    public InnerDevice(String name, String origin, double price, Type type,
                       Calendar dateOfDelivery, String version) {
        super(name, origin, price, type, dateOfDelivery);
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof InnerDevice)) return false;
        if (!super.equals(o)) return false;
        InnerDevice that = (InnerDevice) o;
        return Objects.equals(getVersion(), that.getVersion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getVersion());
    }

    @Override
    public String toString() {
        return "InnerDevice{" + super.toString() +
                "\nversion='" + version + '\'' +
                '}';
    }
}
