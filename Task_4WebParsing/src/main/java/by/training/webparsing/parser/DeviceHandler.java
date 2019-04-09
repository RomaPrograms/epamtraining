package by.training.webparsing.parser;

import by.training.webparsing.entity.Parameter;
import by.training.webparsing.entity.Device;
import by.training.webparsing.entity.PeripheralDevice;
import by.training.webparsing.entity.InnerDevice;
import by.training.webparsing.entity.Port;
import by.training.webparsing.entity.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.util.EnumSet;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Class which contains methods for parsing by SAX paring.
 */
public class DeviceHandler extends DefaultHandler {

    /**
     * List with devices.
     */
    private List<Device> devices;
    /**
     * Set with parameters.
     */
    private EnumSet<Parameter> enumSet;
    /**
     * Instance of peripheral device.
     */
    private PeripheralDevice peripheralDevice;
    /**
     * Instance of inner device.
     */
    private InnerDevice innerDevice;
    /**
     * Current parameter of device which we read from XML file.
     */
    private Parameter currentParam;
    /**
     * Boolean that shows which kind of device we are currently parsing.
     */
    private boolean isInnerDevice;

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(DeviceHandler.class);

    /**
     * Constructor which initialize devices property and enumSet property.
     *
     * @param devicesList - list with devices
     */
    public DeviceHandler(final List<Device> devicesList) {
        devices = devicesList;
        enumSet = EnumSet.range(Parameter.NAME, Parameter.VERSION);
    }

    /**
     * Gets the list with devices.
     *
     * @return list with devices
     */
    public List<Device> getDevices() {
        return devices;
    }

    /**
     * Sets the list with devices.
     *
     * @param devicesList - list with devices
     */
    public void setDevices(final List<Device> devicesList) {
        this.devices = devicesList;
    }

    /**
     * Method that shows that parsing by SAX parser began.
     */
    @Override
    public void startDocument() {
        LOGGER.info("Parsing xml-file began by Sax parser.");
    }

    /**
     * Receive notification of the start of an element.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the start of
     * each element (such as allocating a new tree node or writing
     * output to a file).</p>
     *
     * @param uri       The Namespace URI, or the empty string if the
     *                  element has no Namespace URI or if Namespace
     *                  processing is not being performed.
     * @param localName The local name (without prefix), or the
     *                  empty string if Namespace processing is not being
     *                  performed.
     * @param qName     The qualified name (with prefix), or the
     *                  empty string if qualified names are not available.
     * @param attrs     The attributes attached to the element.  If
     *                  there are no attributes, it shall be an empty
     *                  Attributes object.
     */
    @Override
    public void startElement(final String uri, final String localName,
                             final String qName, final Attributes attrs) {
        if (qName.equals("peripheralDevice")) {
            peripheralDevice = new PeripheralDevice();
            isInnerDevice = false;
        } else {
            if (qName.equals("innerDevice")) {
                innerDevice = new InnerDevice();
                isInnerDevice = true;
            } else {
                if (qName.equals("type")) {
                    handleTypeParameter(attrs);
                } else {
                    Parameter temp = Parameter.valueOf(qName.toUpperCase());
                    if (enumSet.contains(temp)) {
                        currentParam = temp;
                    }
                }
            }
        }
    }

    /**
     * Method which handle data from tag "type".
     * @param attrs - attributes of type tag
     */
    private void handleTypeParameter(final Attributes attrs) {
        if (isInnerDevice) {
            innerDevice.getType().setCooler(Boolean
                    .parseBoolean(attrs.getValue("cooler")));
            innerDevice.getType()
                    .setCritical(Boolean.parseBoolean(attrs
                            .getValue("critical")));
            innerDevice.getType()
                    .setPort(Port.valueOf(attrs
                            .getValue("ports")));
        } else {
            peripheralDevice.getType().setCooler(Boolean
                    .parseBoolean(attrs.getValue("cooler")));
            peripheralDevice.getType()
                    .setCritical(Boolean.parseBoolean(attrs
                            .getValue("critical")));
            peripheralDevice.getType().setPort(Port
                    .valueOf(attrs.getValue("ports")));
        }
    }

    /**
     * Receive notification of character data inside an element.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method to take specific actions for each chunk of character data
     * (such as adding the data to a node or buffer, or printing it to
     * a file).</p>
     *
     * @param ch     The characters.
     * @param start  The start position in the character array.
     * @param length The number of characters to use from the
     *               character array.
     */
    @Override
    public void characters(final char[] ch, final int start, final int length) {
        String data = new String(ch, start, length);
        try {
            if (currentParam != null) {
                switch (currentParam) {
                    case NAME:
                        setName(data);
                        break;
                    case ORIGIN:
                        setOrigin(data);
                        break;
                    case PRICE:
                        setPrice(data);
                        break;
                    case POWERUSAGE:
                        setPowerUsage(data);
                        break;
                    case GROUPOFCOMPLECTS:
                        setGroupOfComplects(data);
                        break;
                    case DATEOFDELIVERY:
                        setDateOfDelivery(new String(ch, start, length));
                        break;
                    case CONNECTION:
                        peripheralDevice.setConnection(Connection
                                .valueOf(data.toUpperCase()));
                        break;
                    case VERSION:
                        innerDevice.setVersion(data);
                        break;
                    default:
                        throw new EnumConstantNotPresentException(currentParam
                                .getDeclaringClass(), currentParam.name());
                }
                currentParam = null;
            }
        } catch (IllegalArgumentException | EnumConstantNotPresentException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Method sets name of device.
     * @param data - name of device
     */
    private void setName(final String data) {
        if (isInnerDevice) {
            innerDevice.setName(data);
        } else {
            peripheralDevice.setName(data);
        }
    }

    /**
     * Method sets origin of device.
     * @param data - origin of device
     */
    private void setOrigin(final String data) {
        if (isInnerDevice) {
            innerDevice.setOrigin(data);
        } else {
            peripheralDevice.setOrigin(data);
        }
    }

    /**
     * Method sets price of device.
     * @param data - price of device
     */
    private void setPrice(final String data) {
        if (isInnerDevice) {
            innerDevice.setPrice(Double
                    .parseDouble(data));
        } else {
            peripheralDevice.setPrice(Double
                    .parseDouble(data));
        }
    }

    /**
     * Method sets power usage of device.
     * @param data - power usage of device
     */
    private void setPowerUsage(final String data) {
        if (isInnerDevice) {
            innerDevice.getType().setPowerUsage(Double
                    .parseDouble(data));
        } else {
            peripheralDevice.getType().setPowerUsage(Double
                    .parseDouble(data));
        }
    }

    /**
     * Method sets group of complects of device.
     * @param data - group of complects of device
     */
    private void setGroupOfComplects(final String data) {
        if (isInnerDevice) {
            innerDevice.getType().setGroupOfComplects(data);
        } else {
            peripheralDevice.getType()
                    .setGroupOfComplects(data);
        }
    }
    /**
     * Method sets date of delivery of device.
     * @param string - date of delivery
     */
    private void setDateOfDelivery(final String string) {
        StringTokenizer tokenizer
                = new StringTokenizer(string, "-");
        if (isInnerDevice) {
            innerDevice.setDateOfDelivery(new
                    GregorianCalendar(Integer
                    .parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken())));
        } else {
            peripheralDevice.setDateOfDelivery(new
                    GregorianCalendar(Integer
                    .parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken())));
        }
    }
    /**
     * Receive notification of the end of an element.
     *
     * <p>By default, do nothing.  Application writers may override this
     * method in a subclass to take specific actions at the end of
     * each element (such as finalising a tree node or writing
     * output to a file).</p>
     *
     * @param uri       The Namespace URI, or the empty string if the
     *                  element has no Namespace URI or if Namespace
     *                  processing is not being performed.
     * @param localName The local name (without prefix), or the
     *                  empty string if Namespace processing is not being
     *                  performed.
     * @param qName     The qualified name (with prefix), or the
     *                  empty string if qualified names are not available.
     */
    @Override
    public void endElement(final String uri, final String localName,
                           final String qName) {
        if (qName.equals("peripheralDevice")) {
            devices.add(peripheralDevice);
        }
        if (qName.equals("innerDevice") && isInnerDevice) {
            devices.add(innerDevice);
        }

    }

    /**
     * Receive notification of the end of the document.
     */
    @Override
    public void endDocument() {
        LOGGER.info("Parsing xml-file ended by Sax parser.");
    }
}
