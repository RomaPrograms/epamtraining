package by.training.webparsing.parser;

import by.training.webparsing.entity.Connection;
import by.training.webparsing.entity.Device;
import by.training.webparsing.entity.PeripheralDevice;
import by.training.webparsing.entity.InnerDevice;
import by.training.webparsing.entity.Port;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Class for parsing by DOM parser.
 */
public class DevicesDOMBuilder extends AbstractDeviceBuilder {
    /**
     * Instance of {@code DocumentBuilder} class.
     */
    private DocumentBuilder docBuilder;

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(DeviceHandler.class);

    /**
     * Constructor which initializes docBuilder property.
     */
    public DevicesDOMBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error("Issue of configuration parser.");
        }
    }

    /**
     * Constructor which initializes docBuilder property and list with devices.
     *
     * @param devices - created devices
     */
    public DevicesDOMBuilder(final List<Device> devices) {
        super(devices);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            LOGGER.error("Issue of configuration parser.");
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
        Document document;
        try {
            document = docBuilder.parse(new File(fileName));
            NodeList peripheralDevices = document.getDocumentElement()
                    .getElementsByTagName("peripheralDevice");
            NodeList innerDevices = document.getDocumentElement()
                    .getElementsByTagName("innerDevice");

            for (int i = 0; i < peripheralDevices.getLength(); i++) {
                Node node = peripheralDevices.item(i);
                getDevices().add(createPeripheralDevice(node));
            }

            for (int i = 0; i < innerDevices.getLength(); i++) {
                Node node = innerDevices.item(i);
                getDevices().add(createInnerDevice(node));
            }

            LOGGER.info("Parsing by DOM parser was successfully done!");
        } catch (IOException e) {
            LOGGER.error("File error or I/O error.");
        } catch (SAXException e) {
            LOGGER.error("Parsing failure.");
        }
    }

    /**
     * Method that during parsing creates PeripheralDevice.
     *
     * @param node - node of XML file where stores data about peripheral device
     * @return - object of peripheral device after parsing
     */
    private static PeripheralDevice createPeripheralDevice(final Node node) {
        PeripheralDevice peripheralDevice = new PeripheralDevice();
        addCommonInformation(peripheralDevice, (Element) node);
        peripheralDevice.setConnection(Connection
                .valueOf(getElementTextContent((Element) node,
                        "connection").toUpperCase()));
        return peripheralDevice;
    }

    /**
     * Method that during parsing creates InnerDevice.
     *
     * @param node - node of XML file where stores data about inner device
     * @return - object of inner device after parsing
     */
    private static InnerDevice createInnerDevice(final Node node) {
        InnerDevice innerDevice = new InnerDevice();
        addCommonInformation(innerDevice, (Element) node);
        innerDevice.setVersion(getElementTextContent((Element) node,
                "version"));
        return innerDevice;
    }

    /**
     * Methods parses common information of all devices and initialize them.
     *
     * @param device  - device which we will initialize.
     * @param element - element of node of XML file where stores data about
     *                inner device
     */
    private static void addCommonInformation(final Device device,
                                             final Element element) {
        device.setName(getElementTextContent(element, "name"));
        device.setOrigin(getElementTextContent(element,
                "origin"));
        device.setPrice(Double.parseDouble(getElementTextContent(element,
                "price")));
        Element elementType = (Element) element.getElementsByTagName("type")
                .item(0);
        device.getType().setPowerUsage(Double
                .parseDouble(getElementTextContent(elementType,
                        "powerUsage")));
        device.getType()
                .setGroupOfComplects(getElementTextContent(elementType,
                        "groupOfComplects"));
        device.getType().setCooler(Boolean.parseBoolean(elementType
                .getAttribute("cooler")));
        device.getType().setPort(Port.valueOf(elementType
                .getAttribute("ports")));
        if (elementType.hasAttribute("critical")) {
            device.getType().setCritical(Boolean.parseBoolean(elementType
                    .getAttribute("critical")));
        }

        String dateOfDelivery = getElementTextContent(element,
                "dateOfDelivery");
        StringTokenizer tokenizer = new StringTokenizer(dateOfDelivery,
                "-");
        device.setDateOfDelivery(new
                GregorianCalendar(Integer
                .parseInt(tokenizer.nextToken()),
                Integer.parseInt(tokenizer.nextToken()),
                Integer.parseInt(tokenizer.nextToken())));
    }

    /**
     * Methods that takes value of parameter for device from xml tag.
     *
     * @param element     - tag from xml
     * @param elementName - name of parameter
     * @return string with value from element
     */
    private static String getElementTextContent(final Element element,
                                                final String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
