package by.training.webparsing.parser;

import by.training.webparsing.entity.Device;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDeviceBuilder {
    private List<Device> devices;

    public AbstractDeviceBuilder() {
        devices = new ArrayList<>();
    }

    public AbstractDeviceBuilder(List<Device> devices) {
        this.devices = devices;
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    abstract public void buildListDevices(String fileName);
}
