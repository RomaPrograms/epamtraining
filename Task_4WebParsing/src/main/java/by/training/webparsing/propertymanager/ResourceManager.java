package by.training.webparsing.propertymanager;

import java.sql.Driver;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Class for getting data from {@code ResourceBundle} on different languages.
 */
public final class ResourceManager {
    /**
     * Instance of {@code ResourceManager} class for using Singleton.
     */
    public static final ResourceManager INSTANCE = new ResourceManager();
    /**
     * Instance of {@code ResourceBundle} class for using different
     * languageProp.
     */
    private ResourceBundle resourceBundle;
    /**
     * Name of resource with text on different languages.
     */
    private static final String RESOURCE_NAME = "property.text";

    /**
     * Constructor of ResourceManager that initialize resourceBundle property.
     */
    private ResourceManager() {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME,
                Locale.getDefault());
    }

    /**
     * Method that changes languageProp of text form resource bundle.
     * @param locale - locale of Country whose languageProp we will use
     */
    public void changeResource(final Locale locale) {
        resourceBundle = ResourceBundle.getBundle(RESOURCE_NAME, locale);
    }

    /**
     * Gets the string from resource bundle.
     * @param key - name of string
     * @return value of string from resource bundle with key from attributes
     */
    public String getString(final String key) {
        return resourceBundle.getString(key);
    }
}
