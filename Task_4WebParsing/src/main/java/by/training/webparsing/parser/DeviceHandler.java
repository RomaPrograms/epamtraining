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

public class DeviceHandler extends DefaultHandler {

    private List<Device> devices;
    private EnumSet<Parameter> enumSet;
    private PeripheralDevice peripheralDevice;
    private InnerDevice innerDevice;
    private Parameter currentParam;
    private boolean isInnerDevice;

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(DeviceHandler.class);

    public DeviceHandler(final List<Device> devicesList) {
        devices = devicesList;
        enumSet = EnumSet.range(Parameter.NAME, Parameter.VERSION);
    }

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(final List<Device> devicesList) {
        this.devices = devicesList;
    }

    @Override
    public void startDocument() {
        LOGGER.info("Parsing xml-file began by Sax parser.");
    }

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
                } else {
                    Parameter temp = Parameter.valueOf(qName.toUpperCase());
                    if (enumSet.contains(temp)) {
                        currentParam = temp;
                    }
                }
            }
        }
    }

    @Override
    public void characters(final char[] ch, final int start, final int length) {
        String data = new String(ch, start, length);
        try {
            if (currentParam != null) {
                switch (currentParam) {
                    case NAME:
                        if (isInnerDevice) {
                            innerDevice.setName(data);
                        } else {
                            peripheralDevice.setName(data);
                        }
                        break;
                    case ORIGIN:
                        if (isInnerDevice) {
                            innerDevice.setOrigin(data);
                        } else {
                            peripheralDevice.setOrigin(data);
                        }
                        break;
                    case PRICE:
                        if (isInnerDevice) {
                            innerDevice.setPrice(Double
                                    .parseDouble(data));
                        } else {
                            peripheralDevice.setPrice(Double
                                    .parseDouble(data));
                        }
                        break;
                    case POWERUSAGE:

                        if (isInnerDevice) {
                            innerDevice.getType().setPowerUsage(Double
                                    .parseDouble(data));
                        } else {
                            peripheralDevice.getType().setPowerUsage(Double
                                    .parseDouble(data));
                        }
                        break;
                    case GROUPOFCOMPLECTS:

                        if (isInnerDevice) {
                            innerDevice.getType().setGroupOfComplects(data);
                        } else {
                            peripheralDevice.getType()
                                    .setGroupOfComplects(data);
                        }
                        break;
                    case DATEOFDELIVERY:
                        String string = new String(ch, start, length);
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
                        break;
                    case CONNECTION:
                        peripheralDevice.setConnection(Connection
                                .valueOf(data));
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
        } catch (EnumConstantNotPresentException e) {
            LOGGER.error(e.getMessage());
        }
    }

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

    @Override
    public void endDocument() {
        LOGGER.info("Parsing xml-file ended by Sax parser.");
    }
}
