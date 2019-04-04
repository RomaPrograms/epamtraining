package by.training.webparsing.entity;

import java.util.Calendar;
import java.util.Objects;

public class PeripheralDevice extends Device {
    private Connection connection;

    public  PeripheralDevice() { }

    public PeripheralDevice(Connection connection) {
        this.connection = connection;
    }

    public PeripheralDevice(String name, String origin, double price, Type type,
                            Calendar dateOfDelivery, Connection connection) {
        super(name, origin, price, type, dateOfDelivery);
        this.connection = connection;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PeripheralDevice)) return false;
        PeripheralDevice that = (PeripheralDevice) o;
        return getConnection() == that.getConnection();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getConnection());
    }

    @Override
    public String toString() {
        return "PeripheralDevice{" + super.toString() +
                "\nconnection=" + connection +
                "\n}";
    }
}
