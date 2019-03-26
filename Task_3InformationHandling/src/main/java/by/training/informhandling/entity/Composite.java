package by.training.informhandling.entity;

import by.training.informhandling.parsing.parsingexpression.interpretationtorpn.Calculator;
import by.training.informhandling.parsing.parsingexpression.interpretationtorpn.Client;
import java.util.ArrayList;
import java.util.List;

/**
 * class contains methods and variables for every element of text.
 */
public class Composite implements Component {
    /**
     * list with components of current component.
     */
    private List<Component> components = new ArrayList<>();
    /**
     * category of current component.
     */
    private Category category;
    /**
     * variable for determining will we simply output text or we will work with
     * it.
     */
    private static boolean isOutputText = true;

    /**
     * constructor without parameters.
     */
    public Composite() {
    }

    /**
     * constructor with single parameter.
     * @param textCategory - category of current composit
     */
    public Composite(final Category textCategory) {
        this.category = textCategory;
    }

    /**
     * returns list with components of current component.
     * @return - list with components of current component
     */
    @Override
    public List<Component> getComponents() {
        return components;
    }

    /**
     * returns category of current Component.
     * @return - category
     */
    @Override
    public Category getCategory() {
        return this.category;
    }

    /**
     * sets will we simply output our text or just do some sorting or some
     * operations.
     * @param isReading - boolean variable
     */
    @Override
    public void setIsOutputText(final boolean isReading) {
        isOutputText = isReading;
    }

    /**
     * method add components to list after parsing.
     * @param component - component
     * @param currentCategory - category of component
     */
    @Override
    public void add(final Component component, final Category currentCategory) {
        this.category = currentCategory;
        components.add(component);
    }

    /**
     * method for creating copy of current object.
     * @return - copy of current object
     */
    @Override
    public Component clone(final Component cloneComponent,
                           final Component component) {
        for (int i = 0; i < component.getComponents().size(); i++) {
            Component cloneChild = new Composite(component
                    .getComponents().get(i).getCategory());
            cloneChild = component.getComponents().get(i).clone(cloneChild,
                    this.getComponents().get(i));
            cloneComponent.add(cloneChild, this.getCategory());
        }

        return cloneComponent;
    }

    /**
     * makes string from components of current component.
     * @return - list with components of current component in single string
     */
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (var component : components) {
            stringBuilder.append(component.toString());
        }

        if (category == Category.EXPRESSION && isOutputText) {
            String expression
                    = Calculator.expressionToRPN(stringBuilder.toString());
            stringBuilder.delete(0,
                    stringBuilder.length());
            Client interpreter = new Client(expression);
            stringBuilder.append(interpreter.calculate());
            stringBuilder.append(" ");
        } else {
            if (category == Category.SENTENCE) {
                stringBuilder.append("\n");
            } else {
                if (category == Category.WORD && isOutputText) {
                    stringBuilder.append(" ");
                }
            }
        }

        return stringBuilder.toString();
    }
}
