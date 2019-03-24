package by.training.informhandling.parsing.parsingtexttoelements;

import by.training.informhandling.entity.Category;
import by.training.informhandling.entity.Component;
import by.training.informhandling.entity.Composit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * class with all necessary for parsing Paragraph to Sentences.
 */
public class ParseToSentence extends ParseText {
    /**
     * regular expression for parsing Paragraph to Sentences.
     */
    private static final String REGULAR_EXPRESSION = ".*?([!?]|[.]+)";
    /**
     * pattern for parsing Paragraph to Sentences.
     */
    private Pattern pattern = Pattern.compile(REGULAR_EXPRESSION);
//    private static ParseText parser;

    /**
     * constructor without parameters.
     */
    public ParseToSentence() {
        if (getParser() == null) {
            setParser(new ParseToLexeme());
        }
    }

//    private ParseToSentence() {
//    }
//
//    public static synchronized ParseText getParser() {
//        if(parser == null) {
//            parser = ParseToLexeme.getParser();
//        }
//        return parser;
//    }

    /**
     * methods where Paragraph of text will be parsed to Sentences.
     * @param curTextElement - current element of text
     * @param text - text for parsing
     * @return parsed element of text
     */
    @Override
    public Component parse(final Composit curTextElement, final String text) {
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            Composit sentence = new Composit(Category.SENTENCE);
            curTextElement.add(getParser().parse(sentence, matcher.group()),
                    Category.SENTENCE);
        }

        return curTextElement;
    }
}
