package by.training.webparsing.propertymanager;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceManager {
    public static final ResourceManager INSTANCE = new ResourceManager();
    private ResourceBundle resourceBundle;
    private final String resourceName = "property.text";

    private ResourceManager() {
        resourceBundle = ResourceBundle.getBundle(resourceName, Locale.getDefault());
    }

    public void changeResource(Locale locale) {
        resourceBundle = ResourceBundle.getBundle(resourceName, locale);
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }
}
