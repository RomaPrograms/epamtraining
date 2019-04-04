package by.training.webparsing.parser;

import by.training.webparsing.entity.*;
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

public class DevicesDOMBuilder extends AbstractDeviceBuilder {
    private DocumentBuilder docBuilder;

    public DevicesDOMBuilder() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("Ошибка конфигурации парсера: " + e);
        }
    }

    public DevicesDOMBuilder (List<Device> students) {
        super(students);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("Ошибка конфигурации парсера: " + e);
        }
    }

    @Override
    public void buildListDevices(String fileName) {
        Document document = null;
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
        } catch (IOException e) {
            System.err.println("File error or I/O error: " + e);
        } catch (SAXException e) {
            System.err.println("Parsing failure: " + e);
        }
    }

    private static PeripheralDevice createPeripheralDevice(final Node node) {
        PeripheralDevice peripheralDevice = new PeripheralDevice();
        addCommonInformation(peripheralDevice, (Element) node);
        peripheralDevice.setConnection(Connection
                .valueOf(getElementTextContent((Element) node,
                        "connection")));
        return peripheralDevice;
    }

    private static InnerDevice createInnerDevice(final Node node) {
        InnerDevice innerDevice = new InnerDevice();
        addCommonInformation(innerDevice, (Element) node);
        innerDevice.setVersion(getElementTextContent((Element) node,
                "version"));
        return innerDevice;
    }

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

    private static String getElementTextContent(final Element element,
                                                final String elementName) {
        NodeList nList = element.getElementsByTagName(elementName);
        Node node = nList.item(0);
        return node.getTextContent();
    }
}
