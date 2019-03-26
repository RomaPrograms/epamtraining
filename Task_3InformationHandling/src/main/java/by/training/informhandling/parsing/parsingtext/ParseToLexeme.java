package by.training.informhandling.parsing.parsingtext;

import by.training.informhandling.entity.Category;
import by.training.informhandling.entity.Component;
import by.training.informhandling.entity.Composite;

/**
 * class with all necessary for parsing Sentences to Lexemes.
 */
public final class ParseToLexeme extends ParseText {
    /**
     * constant object of class ParseToLexeme.
     */
    private static final ParseToLexeme INSTANCE = new ParseToLexeme();

    /**
     * returns object of current class for realization Singleton.
     * @return - object of class ParseToLexeme
     */
    public static ParseToLexeme getInstance() {
        return INSTANCE;
    }

    /**
     * sets next parser for realization Chain Of Responsibility.
     */
    private ParseToLexeme() {
        setParser(ParseToWordAndExpression.getInstance());
    }

    /**
     * methods where Sentences of text will be parsed to Lexemes.
     * @param curTextElement - current element of text
     * @param text - text for parsing
     * @return parsed element of text
     */
    @Override
    public Component parse(final Component curTextElement, final String text) {

        String[] mass = text.split("\\s+");
        for (int i = 0; i < mass.length; i++) {
            Composite sentence = new Composite(Category.LEXEME);
            curTextElement.add(getParser().parse(sentence, mass[i]),
                    Category.LEXEME);
        }

        return curTextElement;
    }
}
