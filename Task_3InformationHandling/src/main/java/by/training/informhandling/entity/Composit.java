package by.training.informhandling.entity;

import by.training.informhandling.parsing.parsingexpression.interpretationtorpn.Calculator;
import by.training.informhandling.parsing.parsingexpression.interpretationtorpn.Client;
import java.util.ArrayList;
import java.util.List;

/**
 * class contains methods and variables for every element of text.
 */
public class Composit implements Component, Cloneable {
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
    public Composit() {
    }

    /**
     * constructor with single parameter.
     * @param textCategory - category of current composit
     */
    public Composit(final Category textCategory) {
        this.category = textCategory;
    }

    /**
     * returns list with components of current component.
     * @return - list with components of current component
     */
    public List<Component> getComponents() {
        return components;
    }

    /**
     * sets will we simply output our text or just do some sorting or some
     * operations.
     * @param isReading - boolean variable
     */
    @Override
    public void setIsOutputText(final boolean isReading) {
        this.isOutputText = isReading;
    }

    /**
     * method add components to list after parsing.
     * @param component - component
     * @param currentCategory - category of component
     */
    public void add(final Component component, final Category currentCategory) {
        this.category = currentCategory;
        components.add(component);
    }

    /**
     * method for creating copy of current object.
     * @return - copy of current object
     */
    @Override
    public Composit clone() {
        return (Composit) this.getComponents().get(0).clone();
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
