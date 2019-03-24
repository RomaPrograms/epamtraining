package by.training.informhandling.parsing.parsingtexttoelements;

import by.training.informhandling.entity.Category;
import by.training.informhandling.entity.Component;
import by.training.informhandling.entity.Composit;
import by.training.informhandling.entity.Leaf;

/**
 * class with all necessary for parsing Words and Expressions to Symbols.
 */
public class ParseToSymbol extends ParseText {

    /**
     * methods where Words or Expressions of text will be parsed.
     * @param curTextElement - current element of text
     * @param text - text for parsing
     * @return parsed element of text
     */
    @Override
    public Component parse(final Composit curTextElement, final String text) {
        for (int i = 0; i < text.length(); i++) {
            curTextElement.add(new Leaf(text.charAt(i)),
                    Category.SYMBOL);
        }
        return curTextElement;
    }
}
