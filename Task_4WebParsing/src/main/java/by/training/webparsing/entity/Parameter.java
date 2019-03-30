package by.training.webparsing.entity;

public enum Parameter {
    DEVICES("devices"),
    PERIPHERALDEVICE("peripheralDevice"),
    INNERDEVICE("innerDevice"),
    NAME("name"),
    ORIGIN("origin"),
    PRICE("price"),
    TYPE("type"),
    COOLER("cooler"),
    CRITICAL("critical"),
    PORTS("ports"),
    POWERUSAGE("powerUsage"),
    GROUPOFCOMPLECTS("groupOfComplects"),
    DATEOFDELIVERY("dateOfDelivery"),
    CONNECTION("connection"),
    VERSION("version");

    private String value;
    Parameter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
