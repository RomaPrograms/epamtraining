package by.training.informhandling.parsing.parsingtext;

import by.training.informhandling.entity.Category;
import by.training.informhandling.entity.Component;
import by.training.informhandling.entity.Composit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * class with all necessary for parsing Lexeme to Words and Expressions.
 */
public class ParseToWordAndExpression extends ParseText {
    /**
     * regular expression for parsing all kind of lexeme.
     */
    private static final String REGULAR_EXPRESSION
            = "([A-Za-z]+)|([\\d&~|^<>()]+)|\\W";
    /**
     * regular expression for finding bit expressions in text.
     */
    private static final String BIT_REGULAR_EXPRESSION
            = "([\\d&~|^<>()][\\d&~|^<>()]+)";
    /**
     * pattern for parsing all kind of lexeme.
     */
    private Pattern pattern = Pattern.compile(REGULAR_EXPRESSION);
    /**
     * pattern for finding bit expressions in text.
     */
    private Pattern pattern1 = Pattern.compile(BIT_REGULAR_EXPRESSION);
//    private static ParseText parser;

    /**
     * constructor without parameters.
     */
    public ParseToWordAndExpression() {
        if (getParser() == null) {
            setParser(new ParseToSymbol());
        }
    }

//    private ParseToWordAndExpression() { }
//
//    public static synchronized ParseText getParser() {
//            if(parser == null) {
//                parser = new ParseToSymbol();
//            }
//            return parser;
//    }

    /**
     * methods where Lexeme of text will be parsed to Words and Expressions.
     * @param curTextElement - current element of text
     * @param text - text for parsing
     * @return parsed element of text
     */
    @Override
    public Component parse(final Composit curTextElement, final  String text) {
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            Matcher matcher1 = pattern1.matcher(matcher.group());
            if (matcher1.find() && matcher1.group().length() >= 2) {
                Composit wordAndExpression = new Composit(Category.EXPRESSION);
                curTextElement.add(getParser().parse(wordAndExpression,
                        matcher.group()), Category.EXPRESSION);
            } else {
                Composit wordAndExpression = new Composit(Category.WORD);
                curTextElement.add(getParser().parse(wordAndExpression,
                        matcher.group()), Category.WORD);
            }
        }
        return curTextElement;
    }
}
