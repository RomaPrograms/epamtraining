package by.training.informhandling.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * interface with all common methods and variables for every text element.
 */
public interface Component {
    /**
     * list with components of current component.
     * @return - list with components
     */
    default List<Component> getComponents() {
        return new ArrayList<>();
    }

    /**
     * returns category of current component.
     * @return - category of current component
     */
    Category getCategory();

    /**
     * sets will we simply output our text or just do some sorting or some
     * operations.
     * @param isOutputText - boolean variable
     */
    default void setIsOutputText(boolean isOutputText) { }

    /**
     * method add components to list after parsing.
     * @param c - component
     * @param category - category of component
     */
    default void add(Component c, Category category) { }

    /**
     * method for creating copy of this class.
     * @param cloneComponent - component for cloning
     * @param component - component which we will clone
     * @return - copy object of this class
     */
    Component clone(Component cloneComponent, Component component);
}
