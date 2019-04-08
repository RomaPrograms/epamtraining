package by.training.webparsing.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DevicesBuilderFactory {

    private static final Logger LOGGER
            = LogManager.getLogger(DevicesBuilderFactory.class);

    private enum TypeParser {
        SAX, STAX, DOM
    }

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
        return null;//TODO What can I return instead of NULL in such situation?
    }
}
