package by.training.webparsing.parser;

import by.training.webparsing.entity.Device;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Class for parsing by SAX parser.
 */
public class DevicesSAXBuilder extends AbstractDeviceBuilder {
    private SAXParser parser;
    private DeviceHandler deviceHandler;

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(DeviceHandler.class);

    public DevicesSAXBuilder() {
        try {
            parser = SAXParserFactory.newInstance().newSAXParser();
            deviceHandler = new DeviceHandler(getDevices());
        } catch (ParserConfigurationException e) {
            LOGGER.error("Issue of configuration parser.");
        } catch (SAXException e) {
            LOGGER.error("Parsing failure.");
        }
    }

    public DevicesSAXBuilder(final List<Device> students) {
        super(students);
        try {
            parser = SAXParserFactory.newInstance().newSAXParser();
            deviceHandler = new DeviceHandler(getDevices());
        } catch (ParserConfigurationException e) {
            LOGGER.error("Issue of configuration parser.");
        } catch (SAXException e) {
            LOGGER.error("Parsing failure.");
        }
    }

    /**
     * Method that gets some name of file, parses data from it and initialize
     * list of devices.
     *
     * @param fileName - name of file with data about devices.
     */
    @Override
    public void buildListDevices(final String fileName) {
        try {
            parser.parse(new File(fileName), deviceHandler);
        } catch (SAXException e) {
            LOGGER.error("Parsing failure.");
        } catch (IOException e) {
            LOGGER.error("File failure.");
        }
    }
}
