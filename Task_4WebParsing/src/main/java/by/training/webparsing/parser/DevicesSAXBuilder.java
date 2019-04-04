package by.training.webparsing.parser;

import by.training.webparsing.entity.Device;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class DevicesSAXBuilder extends AbstractDeviceBuilder {
    private SAXParser parser;
    private DeviceHandler deviceHandler;

    public DevicesSAXBuilder() {
        try {
            parser = SAXParserFactory.newInstance().newSAXParser();
            deviceHandler = new DeviceHandler(getDevices());
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public DevicesSAXBuilder (List<Device> students) {
        super(students);
        try {
        parser = SAXParserFactory.newInstance().newSAXParser();
        deviceHandler = new DeviceHandler(getDevices());
    } catch (ParserConfigurationException e) {
        e.printStackTrace();
    } catch (SAXException e) {
        e.printStackTrace();
    }
    }

    @Override
    public void buildListDevices(String fileName) {
        try {
            parser.parse(new File(fileName), deviceHandler);
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
