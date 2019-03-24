package by.training.informhandling.parsing.parsingtexttoelements;

import by.training.informhandling.entity.Category;
import by.training.informhandling.entity.Component;
import by.training.informhandling.entity.Composit;

/**
 * class with all necessary for parsing Sentences to Lexemes.
 */
public class ParseToLexeme extends ParseText {
//    private static ParseText parser;

    /**
     * constructor without parameters.
     */
    public ParseToLexeme() {
        if (getParser() == null) {
            setParser(new ParseToWordAndExpression());
        }
    }

//    private ParseToLexeme() {
//    }
//
//    public static synchronized ParseText getParser() {
//        if (parser == null) {
//            parser = ParseToWordAndExpression.getParser();
//        }
//        return parser;
//    }

    /**
     * methods where Sentences of text will be parsed to Lexemes.
     * @param curTextElement - current element of text
     * @param text - text for parsing
     * @return parsed element of text
     */
    @Override
    public Component parse(final Composit curTextElement, final String text) {

        String[] mass = text.split("\\s+");
        for (int i = 0; i < mass.length; i++) {
            Composit sentence = new Composit(Category.LEXEME);
            curTextElement.add(getParser().parse(sentence, mass[i]),
                    Category.LEXEME);
        }

        return curTextElement;
    }
}
