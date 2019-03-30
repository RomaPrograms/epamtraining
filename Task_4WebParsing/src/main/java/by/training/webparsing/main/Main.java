package by.training.webparsing.main;

import by.training.webparsing.entity.*;
import by.training.webparsing.parser.DeviceHandler;
import by.training.webparsing.parser.DeviceStAXBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    public static void main(final String args[]) {
        //parseDataBySaxParser();
        parseDataByDomParser();
        //parseDataByStAxParser();
//        DeviceStAXBuilder deviceStAXBuilder = new DeviceStAXBuilder();
//        deviceStAXBuilder
//                .buildListOfDevices("D:\\инфа\\EPAM\\05_JavaST_2019\\"
//                + "Task_4WebParsing\\src\\main\\resources\\data"
//                + "\\devices.xml");
//        List<DeviceType> list = deviceStAXBuilder.getDevices();
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).toString());
//        }
    }

    public static void parseDataByStAxParser() {
        List<DeviceType> devices = new ArrayList<>();

    }

    public static void parseDataByDomParser() {
        try {
            List<DeviceType> devices = new ArrayList<>();

            DocumentBuilderFactory factory
                    = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder
                    .parse(new File("D:\\инфа\\EPAM\\05_JavaST_2019\\"
                            + "Task_4WebParsing\\src\\main\\resources\\data"
                            + "\\devices.xml"));

            NodeList peripheralDevices = document.getDocumentElement()
                    .getElementsByTagName("peripheralDevice");
            NodeList innerDevices = document.getDocumentElement()
                    .getElementsByTagName("innerDevice");

            for (int i = 0; i < peripheralDevices.getLength(); i++) {
                Node node = peripheralDevices.item(i);
                devices.add(createPeripheralDevice(node));
            }

            for (int i = 0; i < innerDevices.getLength(); i++) {
                Node node = innerDevices.item(i);
                devices.add(createInnerDevice(node));
            }

            for (int i = 0; i < devices.size(); i++) {
                System.out.println(devices.get(i).toString());
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PeripheralDevice createPeripheralDevice(final Node node) {
        PeripheralDevice peripheralDevice = new PeripheralDevice();
        addCommonInformation(peripheralDevice, (Element) node);
        peripheralDevice.setConnection(Connection
                .valueOf(getElementTextContent((Element) node,
                        "connection")));
        return peripheralDevice;
    }

    public static InnerDevice createInnerDevice(final Node node) {
        InnerDevice innerDevice = new InnerDevice();
        addCommonInformation(innerDevice, (Element) node);
        innerDevice.setVersion(getElementTextContent((Element) node,
                "version"));
        return innerDevice;
    }

    private static void addCommonInformation(final DeviceType deviceType,
                                             final Element element) {
        deviceType.setName(getElementTextContent(element, "name"));
        deviceType.setOrigin(getElementTextContent(element,
                "origin"));
        deviceType.setPrice(Double.parseDouble(getElementTextContent(element,
                "price")));
        Element elementType = (Element) element.getElementsByTagName("type")
                .item(0);
        deviceType.getType().setPowerUsage(Double
                .parseDouble(getElementTextContent(elementType,
                        "powerUsage")));
        deviceType.getType()
                .setGroupOfComplects(getElementTextContent(elementType,
                        "groupOfComplects"));
        deviceType.getType().setCooler(Boolean.parseBoolean(elementType
                .getAttribute("cooler")));
        deviceType.getType().setPort(Port.valueOf(elementType
                .getAttribute("ports")));
        if (elementType.hasAttribute("critical")) {
            deviceType.getType().setCritical(Boolean.parseBoolean(elementType
                    .getAttribute("critical")));
        }

        String dateOfDelivery = getElementTextContent(element,
                "dateOfDelivery");
        StringTokenizer tokenizer = new StringTokenizer(dateOfDelivery,
                "-");
        deviceType.setDateOfDelivery(new
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

    public static void parseDataBySaxParser() {
        try {
            List<DeviceType> devices = new ArrayList<>();
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            DeviceHandler handler = new DeviceHandler(devices);
            parser.parse(new File("D:\\инфа\\EPAM\\05_JavaST_2019\\"
                    + "Task_4WebParsing\\src\\main\\resources\\data"
                    + "\\devices.xml"), handler);

            for (int i = 0; i < devices.size(); i++) {
                System.out.println(devices.get(i) + "\n");
            }
        } catch (SAXException e) {
            System.err.print("ошибка SAX парсера " + e);
        } catch (IOException e) {
            System.err.print("ошибка I/О потока " + e);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
