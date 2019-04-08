package by.training.webparsing.entity;

/**
 * Enum contains parameters of all entity classes.
 */
public enum Parameter {
    /**
     * Parameter DEVICES.
     */
    DEVICES("devices"),
    /**
     * Parameter PERIPHERALDEVICE.
     */
    PERIPHERALDEVICE("peripheralDevice"),
    /**
     * Parameter INNERDEVICE.
     */
    INNERDEVICE("innerDevice"),
    /**
     * Parameter NAME.
     */
    NAME("name"),
    /**
     * Parameter ORIGIN.
     */
    ORIGIN("origin"),
    /**
     * Parameter PRICE.
     */
    PRICE("price"),
    /**
     * Parameter TYPE.
     */
    TYPE("type"),
    /**
     * Parameter COOLER.
     */
    COOLER("cooler"),
    /**
     * Parameter CRITICAL.
     */
    CRITICAL("critical"),
    /**
     * Parameter PORTS.
     */
    PORTS("ports"),
    /**
     * Parameter POWERUSAGE.
     */
    POWERUSAGE("powerUsage"),
    /**
     * Parameter GROUPOFCOMPLECTS.
     */
    GROUPOFCOMPLECTS("groupOfComplects"),
    /**
     * Parameter DATEOFDELIVERY.
     */
    DATEOFDELIVERY("dateOfDelivery"),
    /**
     * Parameter CONNECTION.
     */
    CONNECTION("connection"),
    /**
     * Parameter VERSION.
     */
    VERSION("version");

    /**
     * Value of enum parameter.
     */
    private String value;

    /**
     * One-argument constructor.
     *
     * @param paramValue - value of parameter
     */
    Parameter(final String paramValue) {
        this.value = paramValue;
    }

    /**
     * Gets the value of type Parameter.
     *
     * @return - parameter of device
     */
    public String getValue() {
        return value;
    }

}
