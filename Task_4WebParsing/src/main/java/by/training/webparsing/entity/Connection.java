package by.training.webparsing.entity;

/**
 * Contain types of connection to devices.
 */
public enum Connection {
    /**
     * Type of connection by bluetooth.
     */
    BLUETOOTH("Bluetooth"),
    /**
     * Type of connection by Wire.
     */
    WIRE("Wire"),
    /**
     * Type of connection by WiFi.
     */
    WIFI("WiFi");

    /**
     * Value of enum connection.
     */
    private String value;

    /**
     * One-argument constructor.
     *
     * @param connectionType - type of connection
     */
    Connection(final String connectionType) {
        this.value = connectionType;
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
