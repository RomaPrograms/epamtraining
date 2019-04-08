package by.training.webparsing.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class that recognize which kind of parsing we will use for parsing data from
 * XML.
 */
public class DevicesBuilderFactory {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER
            = LogManager.getLogger(DevicesBuilderFactory.class);

    /**
     * Enum that contains different types of parsing.
     */
    private enum TypeParser {
        /**
         * SAX type of parsing.
         */
        SAX,
        /**
         * STAX type of parsing.
         */
        STAX,
        /**
         * DOM type of parsing.
         */
        DOM
    }

    /**
     * Method that pinpoint which kind of parsing we will use for parsing data
     * from file.
     *
     * @param typeParser - type of parser
     * @return returns instance of class that will parse data by type that we
     * chose
     */
    public AbstractDeviceBuilder createDeviceBuilder(final String
                                                             typeParser) {
        try {
            TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
            switch (type) {
                case DOM:
                    return new DevicesDOMBuilder();
                case STAX:
                    return new DevicesStAXBuilder();
                case SAX:
                    return new DevicesSAXBuilder();
                default:
                    throw new EnumConstantNotPresentException(type
                            .getDeclaringClass(), type.name());
            }
        } catch (EnumConstantNotPresentException e) { //TODO Is it necessarily to use our oun Exceptions?
            LOGGER.error("Such type of parsing doesn't exist!");
        }
        return null; //TODO What can I return instead of NULL in such situation?
    }
}
