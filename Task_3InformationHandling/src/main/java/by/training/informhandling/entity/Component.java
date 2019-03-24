package by.training.informhandling.entity;

import java.util.List;

/**
 * interface with all common methods and variables for every text element.
 */
public interface Component extends Cloneable {
    /**
     * list with components of current component.
     * @return - list with components
     */
    List<Component> getComponents();

    /**
     * sets will we simply output our text or just do some sorting or some
     * operations.
     * @param isOutputText - boolean variable
     */
    void setIsOutputText(boolean isOutputText);

    /**
     * method add components to list after parsing.
     * @param c - component
     * @param category - category of component
     */
    void add(Component c, Category category);
}
