package by.training.webparsing.main;

import by.training.webparsing.entity.Device;
import by.training.webparsing.exception.ParsingException;
import by.training.webparsing.parser.AbstractDeviceBuilder;
import by.training.webparsing.parser.DevicesBuilderFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * Class that contains method from which starts our program.
 */
final class Main {

    /**
     * No-argument constructor.
     */
    private Main() {
    }
    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(DevicesBuilderFactory.class);

    /**
     * Method that starts our program if we want to see result in console.
     * @param args - arguments from cmd
     */
    public static void main(final String[] args) {
        try {
            DevicesBuilderFactory deviceFactory = new DevicesBuilderFactory();
            AbstractDeviceBuilder builder
                    = deviceFactory.createDeviceBuilder("stax");
            builder.buildListDevices("D:\\инфа\\EPAM\\05_JavaST_2019\\"
                    + "Task_4WebParsing\\src\\main\\resources\\data"
                    + "\\devices.xml");
            List<Device> list = builder.getDevices();

            for (var device : list) {
                LOGGER.info(device);
            }

        } catch (ParsingException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
