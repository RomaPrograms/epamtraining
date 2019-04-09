package action.parsingaction;

import by.training.webparsing.entity.*;
import by.training.webparsing.exception.ParsingException;
import by.training.webparsing.parser.AbstractDeviceBuilder;
import by.training.webparsing.parser.DevicesBuilderFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * class for testing different types of parsers.
 */
public class ParsingAction {

    /**
     * List with devices.
     */
    private List<Device> devices;
    /**
     * List with devices where order like after parsing by DOM parser.
     */
    private List<Device> devicesInOrderForDomParser;
    /**
     * Instance of builder class for using different parsers.
     */
    private DevicesBuilderFactory deviceFactory = new DevicesBuilderFactory();
    /**
     * SAX type of parser.
     */
    private static final String SAX_TYPE = "sax";
    /**
     * DOM type of parser.
     */
    private static final String DOM_TYPE = "dom";
    /**
     * StAX type of parser.
     */
    private static final String STAX_TYPE = "stax";
    /**
     * Path to file which we will parse.
     */
    private static final String FILE_NAME = "D:\\инфа\\EPAM\\05_JavaST_2019\\"
            + "Task_4WebParsing\\src\\main\\resources\\data"
            + "\\devicesForTesting.xml";
    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(DevicesBuilderFactory.class);

    /**
     * method calls before other methods of this class.
     */
    @BeforeClass
    public void initialiseParsingAction() {
        devices = new ArrayList<>();
        devices.add(new PeripheralDevice("Defender Doom Keeper GK-100DL",
                "Belarus", 100, new Type(true, false,
                500, "input-output device", Port.USB),
                new GregorianCalendar(2018, 11, 2),
                Connection.BLUETOOTH));
        devices.add(new InnerDevice("ASUS Mining Radeon RX 580 8GB GDDR5",
                "Russia", 150, new Type(false, true,
                400, "video card", Port.COM),
                new GregorianCalendar(2014, 9, 11),
                "version-1.4"));
        devices.add(new PeripheralDevice("Logitech C922 (960-001088)",
                "China", 227, new Type(false, false,
                50, "web-camera", Port.USB),
                new GregorianCalendar(2013, 5, 25),
                Connection.WIRE));

        devicesInOrderForDomParser = new ArrayList<>();
        devicesInOrderForDomParser.add(new PeripheralDevice(
                "Defender Doom Keeper GK-100DL",
                "Belarus", 100, new Type(true, false,
                500, "input-output device", Port.USB),
                new GregorianCalendar(2018, 11, 2),
                Connection.BLUETOOTH));
        devicesInOrderForDomParser.add(new PeripheralDevice(
                "Logitech C922 (960-001088)",
                "China", 227, new Type(false, false,
                50, "web-camera", Port.USB),
                new GregorianCalendar(2013, 5, 25),
                Connection.WIRE));
        devicesInOrderForDomParser.add(new InnerDevice(
                "ASUS Mining Radeon RX 580 8GB GDDR5",
                "Russia", 150, new Type(false, true,
                400, "video card", Port.COM),
                new GregorianCalendar(2014, 9, 11),
                "version-1.4"));
    }

    /**
     * Method that creates data for {@code parsingAction} method.
     * @return - data for {@code parsingAction} method
     */
    @DataProvider(name = "dataProviderForParsingAction")
    public Object[][] dataProviderForParsingAction() {
        return new Object[][]{{devices, SAX_TYPE}, {devices, STAX_TYPE},
                {devicesInOrderForDomParser, DOM_TYPE}};
    }

    /**
     * Tests different type of parser on correct parsing.
     *
     * @param devices - expected list with devices
     * @param typeOfParser - type of parser.
     */
    @Test(dataProvider = "dataProviderForParsingAction")
    public void parsingAction(List<Device> devices, String typeOfParser) {
        try {
            AbstractDeviceBuilder builder = deviceFactory
                    .createDeviceBuilder(typeOfParser);
            builder.buildListDevices(FILE_NAME);
            List<Device> actual = builder.getDevices();
            Assert.assertEquals(actual, devices);
        } catch (ParsingException e) {
            LOGGER.error("Issue with parsers.");
        }
    }

}
