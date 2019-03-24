package by.training.informhandling.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * class with last level component called leaf.
 */
public class Leaf implements Component, Cloneable {
    /**
     * single symbol from word.
     */
    private char symbol;

    /**
     * constructor with single parameter.
     * @param text - word or expression
     */
    public Leaf(final char text) {
        this.symbol = text;
    }

    @Override
    public void setIsOutputText(final boolean isOutputText) {
        System.out.println("-------------");
    }

    //у меня получается, что методы в Leaf бесполезно перегружать,
    //что с этим делать?
    public void add(final Component c, final Category category) {
        System.out.println("Leaf -> add. Doing nothing");
    }

    /**
     * returns empty list cause it's last level component.
     * @return - list with components of current component
     */
    @Override
    public List<Component> getComponents() {
        return new ArrayList<>();
    }

    /**
     * method creates string from symbol.
     * @return - symbol
     */
    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

//    @Override
//    public Leaf clone() {
//        return this.clone();
//    }
}
