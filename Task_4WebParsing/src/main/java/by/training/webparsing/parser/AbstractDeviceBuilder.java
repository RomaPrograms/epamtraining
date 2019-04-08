package by.training.webparsing.parser;

import by.training.webparsing.entity.Device;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that contains list with devices.
 */
public abstract class AbstractDeviceBuilder {
    /**
     * List with devices.
     */
    private List<Device> devices;

    /**
     * No-arguments constructor that creates instance of ArrayList.
     */
    public AbstractDeviceBuilder() {
        devices = new ArrayList<>();
    }

    /**
     * One-arguments constructor that initializes list with devices.
     *
     * @param deviceList - list with devices
     */
    public AbstractDeviceBuilder(final List<Device> deviceList) {
        this.devices = deviceList;
    }

    /**
     * Gets list with devices (devices property).
     *
     * @return list with devices
     */
    public List<Device> getDevices() {
        return devices;
    }

    /**
     * Sets list with devices (devices property).
     *
     * @param deviceList - list with devices
     */
    public void setDevices(final List<Device> deviceList) {
        this.devices = deviceList;
    }

    /**
     * Method that fulfill list with devices.
     *
     * @param fileName - name of file with data about devices.
     */
    public abstract void buildListDevices(String fileName);
}
