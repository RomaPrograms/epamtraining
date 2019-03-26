package by.training.informhandling.parsing.parsingtext;

import by.training.informhandling.entity.Category;
import by.training.informhandling.entity.Component;
import by.training.informhandling.entity.Leaf;

/**
 * class with all necessary for parsing Words and Expressions to Symbols.
 */
public final class ParseToSymbol extends ParseText {
    /**
     * constant object of class ParseToSymbol.
     */
    private static final ParseToSymbol INSTANCE = new ParseToSymbol();

    /**
     * returns object of current class for realization Singleton.
     * @return - object of class ParseToSymbol
     */
    public static ParseToSymbol getInstance() {
        return INSTANCE;
    }

    /**
     * methods where Words or Expressions of text will be parsed.
     * @param curTextElement - current element of text
     * @param text - text for parsing
     * @return parsed element of text
     */
    @Override
    public Component parse(final Component curTextElement, final String text) {
        for (int i = 0; i < text.length(); i++) {
            curTextElement.add(new Leaf(text.charAt(i)),
                    Category.SYMBOL);
        }
        return curTextElement;
    }
}
