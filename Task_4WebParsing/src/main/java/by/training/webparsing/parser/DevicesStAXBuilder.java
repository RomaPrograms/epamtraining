package by.training.webparsing.parser;

import by.training.webparsing.entity.InnerDevice;
import by.training.webparsing.entity.Device;
import by.training.webparsing.entity.PeripheralDevice;
import by.training.webparsing.entity.Parameter;
import by.training.webparsing.entity.Connection;
import by.training.webparsing.entity.Port;
import by.training.webparsing.exception.ParsingException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Class for parsing by StAX parser.
 */
public class DevicesStAXBuilder extends AbstractDeviceBuilder {
    /**
     * Instance of {@code XMLInputFactory} for StAX paring.
     */
    private XMLInputFactory inputFactory;

    /**
     * Number of tags that we should pass for initializing parameter Type.
     */
    private static final int TAGS_NUMBER_FOR_TYPE = 4;
    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(DeviceHandler.class);

    /**
     * Constructor that initialize inputFactory property.
     */
    public DevicesStAXBuilder() {
        super();
        inputFactory = XMLInputFactory.newInstance();
    }

    /**
     * Constructor that initialize inputFactory property and list with devices.
     *
     * @param devices - devices from file
     */
    public DevicesStAXBuilder(final List<Device> devices) {
        super(devices);
        inputFactory = XMLInputFactory.newInstance();
    }

    /**
     * Method that gets some name of file, parses data from it and initialize
     * list of devices.
     *
     * @param fileName - name of file with data about devices.
     */
    @Override
    public void buildListDevices(final String fileName) {
        XMLStreamReader reader;
        String name;
        try (FileInputStream inputStream
                     = new FileInputStream(new File(fileName))) {
            reader = inputFactory.createXMLStreamReader(inputStream);

            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    parseSpecificDevice(name, reader);
                }
            }

            LOGGER.info("Parsing by DOM parser was successfully done!");
        } catch (XMLStreamException ex) {
            LOGGER.error("StAX parsing error!");
        } catch (FileNotFoundException ex) {
            LOGGER.error("File " + fileName + " not found!");
        } catch (ParsingException e) {
            LOGGER.error(e.getMessage());
        } catch (IOException e) {
            LOGGER.error("Exception with file.");
        }
    }

    /**
     * Method that parse device which we found.
     *
     * @param name   - name of device
     * @param reader - reader for parsing XML files by StAX parser
     * @throws ParsingException   - some problems with parsers
     * @throws XMLStreamException - exception with stream issue
     */
    private void parseSpecificDevice(final String name,
                                     final XMLStreamReader reader)
            throws ParsingException, XMLStreamException {
        if (Parameter.valueOf(name.toUpperCase())
                == Parameter.PERIPHERALDEVICE) {
            PeripheralDevice peripheralDevice
                    = buildPeripheralDevice(reader);
            getDevices().add(peripheralDevice);
        } else {
            if (Parameter.valueOf(name.toUpperCase())
                    == Parameter.INNERDEVICE) {
                InnerDevice innerDevice = buildInnerDevice(reader);
                getDevices().add(innerDevice);
            }
        }
    }

    /**
     * Method that creates instance of {@code PeripheralDevice} during
     * StAX parsing.
     *
     * @param reader - reader for parsing XML files by StAX parser
     * @return returns initialized peripheral device
     * @throws XMLStreamException - exception with stream issue
     * @throws ParsingException   - some problems with parsers
     */
    private PeripheralDevice buildPeripheralDevice(final XMLStreamReader reader)
            throws XMLStreamException, ParsingException {
        PeripheralDevice peripheralDevice = new PeripheralDevice();
        buildCommonInformation(peripheralDevice, reader);
        while (reader.hasNext()) {
            int parameter = reader.next();
            if (parameter == XMLStreamReader.START_ELEMENT) {
                String tagName = reader.getLocalName();
                if (Parameter.valueOf(tagName.toUpperCase())
                        .equals(Parameter.CONNECTION)) {
                    String connection = getXMLText(reader);
                    peripheralDevice.setConnection(Connection
                            .valueOf(connection.toUpperCase()));
                    return peripheralDevice;
                }
            }
        }
        return peripheralDevice;
    }

    /**
     * Method that creates instance of {@code InnerDevice} during StAX parsing.
     *
     * @param reader - reader for parsing XML files by StAX parser
     * @return returns initialized peripheral device
     * @throws XMLStreamException - exception with stream issue
     * @throws ParsingException   - some problems with parsers
     */
    private InnerDevice buildInnerDevice(final XMLStreamReader reader)
            throws XMLStreamException, ParsingException {
        InnerDevice innerDevice = new InnerDevice();
        buildCommonInformation(innerDevice, reader);
        while (reader.hasNext()) {
            int parameter = reader.next();
            if (parameter == XMLStreamReader.START_ELEMENT) {
                String tagName = reader.getLocalName();
                if (Parameter.valueOf(tagName.toUpperCase())
                        .equals(Parameter.VERSION)) {
                    innerDevice.setVersion(getXMLText(reader));
                    return innerDevice;
                }
            }
        }
        return innerDevice;
    }

    /**
     * Method that initialize device during StAX parsing.
     *
     * @param reader - reader for parsing XML files by StAX parser
     * @param device - device which we will initialize
     * @throws XMLStreamException - exception with stream issue
     * @throws ParsingException   - some problems with parsers
     */
    private void buildCommonInformation(final Device device,
                                        final XMLStreamReader reader)
            throws XMLStreamException, ParsingException {
        String tagName;
        int numberOfCommonTags = 0;
        while (reader.hasNext()) {
            int parameter = reader.next();
            switch (parameter) {
                case XMLStreamReader.START_ELEMENT:
                    tagName = reader.getLocalName();
                    switch (Parameter.valueOf(tagName.toUpperCase())) {
                        case NAME:
                            device.setName(getXMLText(reader));
                            break;
                        case ORIGIN:
                            device.setOrigin(getXMLText(reader));
                            break;
                        case PRICE:
                            device.setPrice(Double.parseDouble(getXMLText(
                                    reader)));
                            break;
                        case DATEOFDELIVERY:
                            String date = getXMLText(reader);
                            StringTokenizer tokenizer
                                    = new StringTokenizer(date, "-");
                            device.setDateOfDelivery(new
                                    GregorianCalendar(Integer
                                    .parseInt(tokenizer.nextToken()),
                                    Integer.parseInt(tokenizer.nextToken()),
                                    Integer.parseInt(tokenizer.nextToken())));
                            break;
                        case TYPE:
                            device.getType().setCooler(Boolean
                                    .parseBoolean(reader.getAttributeValue(
                                            null,
                                            Parameter.COOLER.getValue())));
                            device.getType().setPort(Port.valueOf(reader
                                    .getAttributeValue(null,
                                            Parameter.PORTS.getValue())));
                            device.getType().setCritical(Boolean.parseBoolean(
                                    reader.getAttributeValue(null,
                                            Parameter.CRITICAL.getValue())));
                            buildTypeInformation(device, reader);
                            break;
                        default:
                            break;
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
                    if (numberOfCommonTags == TAGS_NUMBER_FOR_TYPE) {
                        return;
                    }
                    numberOfCommonTags++;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Method that initialize all Type properties of device.
     *
     * @param device - device Type of which we will initialize
     * @param reader - reader for parsing XML files by StAX parser
     * @throws XMLStreamException - exception with stream issue
     * @throws ParsingException   - some problems with parsers
     */
    private void buildTypeInformation(final Device device,
                                      final XMLStreamReader reader)
            throws XMLStreamException, ParsingException {
        String name;
        int numberOfElementsInTypeTag = 0;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamReader.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (Parameter.valueOf(name.toUpperCase())) {
                        case POWERUSAGE:
                            device.getType().setPowerUsage(Double
                                    .parseDouble(getXMLText(reader)));
                            break;
                        case GROUPOFCOMPLECTS:
                            device.getType().setGroupOfComplects(getXMLText(
                                    reader));
                            break;
                        default:
                            throw new ParsingException("Using of illegal tag!");
                    }
                    break;
                case XMLStreamReader.END_ELEMENT:
                    if (numberOfElementsInTypeTag == 1) {
                        return;
                    }
                    numberOfElementsInTypeTag++;
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Gets data from current tag.
     *
     * @param reader - reader for parsing XML files by StAX parser
     * @return data from current tag
     * @throws XMLStreamException - exception with stream issue
     */
    private String getXMLText(final XMLStreamReader reader)
            throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}
