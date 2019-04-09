package by.training.webparsing.parser;

import by.training.webparsing.exception.ParsingException;
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
     * @throws ParsingException - some problems with parsers
     */
    public AbstractDeviceBuilder createDeviceBuilder(
            final String typeParser) throws ParsingException {
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
        } catch (IllegalArgumentException | EnumConstantNotPresentException e) {
            LOGGER.error("Such type of parsing doesn't exist!");
        }

        throw new ParsingException("Type of parser was chosen incorrectly.");
    }
}
