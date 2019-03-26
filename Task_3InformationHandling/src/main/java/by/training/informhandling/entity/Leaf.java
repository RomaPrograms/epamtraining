package by.training.informhandling.entity;

/**
 * class with last level component called leaf.
 */
public class Leaf implements Component {
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

    /**
     * returns category of current component - SYMBOL.
     * @return - category
     */
    @Override
    public Category getCategory() {
        return Category.SYMBOL;
    }

    /**
     * method creates string from symbol.
     * @return - symbol
     */
    @Override
    public String toString() {
        return String.valueOf(symbol);
    }

    /**
     * method for cloning object of Leaf.
     * @param cloneComponent - component for cloning
     * @param component - component which we will clone
     * @return cloned Leaf
     */
    @Override
    public Component clone(final Component cloneComponent,
                           final Component component) {
        return new Leaf(symbol);
    }
}
