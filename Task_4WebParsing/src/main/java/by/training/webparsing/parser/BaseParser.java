package by.training.webparsing.parser;


import by.training.webparsing.entity.InnerDevice;
import by.training.webparsing.entity.PeripheralDevice;
import java.util.Calendar;

public abstract class BaseParser {
    private InnerDevice innerDevice = new InnerDevice();
    private PeripheralDevice peripheralDevice = new PeripheralDevice();
    boolean isInnerParser;

    public InnerDevice getInnerDevice() {
        return innerDevice;
    }
    public PeripheralDevice getPeripheralDevice() {
        return peripheralDevice;
    }

    public boolean isInnerParser() {
        return isInnerParser;
    }

    public void setInnerParser(boolean innerParser) {
        isInnerParser = innerParser;
    }

    public abstract void buildName(String name);
    public abstract void buildOrigin(String origin);
    public abstract void buildPrice(String price);
    public abstract void buildType(String Type);
    public abstract void buildDateOfDelivery(String date);
    public abstract void buildVersion(String version);
    public abstract void buildConnection(String connection);
}
