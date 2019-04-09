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
    /**
     * Instance of {@code SAXParer} class.
     */
    private SAXParser parser;
    /**
     * Instance of {@code DeviceHandler} for handling SAX parsing.
     */
    private DeviceHandler deviceHandler;
    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(DeviceHandler.class);
    /**
     * Message if we get issue with parsers.
     */
    private static final String PARSING_ISSUE = "Parsing issue.";
    /**
     * Constructor initializes parser property and deviceHandler property.
     */
    public DevicesSAXBuilder() {
        try {
            parser = SAXParserFactory.newInstance().newSAXParser();
            deviceHandler = new DeviceHandler(getDevices());
        } catch (ParserConfigurationException e) {
            LOGGER.error("Issue of configuration parser.");
        } catch (SAXException e) {
            LOGGER.error(PARSING_ISSUE);
        }
    }

    /**
     * Constructor initializes parser property and deviceHandler property and
     * add devices to device list.
     *
     * @param devices - devices for device list
     */
    public DevicesSAXBuilder(final List<Device> devices) {
        super(devices);
        try {
            parser = SAXParserFactory.newInstance().newSAXParser();
            deviceHandler = new DeviceHandler(getDevices());
        } catch (ParserConfigurationException e) {
            LOGGER.error("Issue of configuration parser.");
        } catch (SAXException e) {
            LOGGER.error(PARSING_ISSUE);
        }
    }

    /**
     * Method that gets some name of file, parses data from it and initialize
     * list of devices.
     *
     * @param fileName - name of file with data about devices
     */
    @Override
    public void buildListDevices(final String fileName) {
        try {
            parser.parse(new File(fileName), deviceHandler);
            LOGGER.info("Parsing by DOM parser was successfully done!");
        } catch (SAXException e) {
            LOGGER.error(PARSING_ISSUE);
        } catch (IOException e) {
            LOGGER.error("File failure.");
        }
    }
}
