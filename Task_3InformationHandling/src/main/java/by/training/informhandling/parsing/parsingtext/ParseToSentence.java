package by.training.informhandling.parsing.parsingtext;

import by.training.informhandling.entity.Category;
import by.training.informhandling.entity.Component;
import by.training.informhandling.entity.Composite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * class with all necessary for parsing Paragraph to Sentences.
 */
public final class ParseToSentence extends ParseText {
    /**
     * regular expression for parsing Paragraph to Sentences.
     */
    private static final String REGULAR_EXPRESSION = ".*?([!?]|[.]+)";
    /**
     * pattern for parsing Paragraph to Sentences.
     */
    private Pattern pattern = Pattern.compile(REGULAR_EXPRESSION);
    /**
     * constant object of class ParseToSentence.
     */
    private static final ParseToSentence INSTANCE = new ParseToSentence();

    /**
     * returns object of current class for realization Singleton.
     * @return - object of class ParseToSentence
     */
    public static ParseToSentence getInstance() {
        return INSTANCE;
    }

    /**
     * sets next parser for realization Chain Of Responsibility.
     */
    private ParseToSentence() {
        setParser(ParseToLexeme.getInstance());
    }


    /**
     * methods where Paragraph of text will be parsed to Sentences.
     * @param curTextElement - current element of text
     * @param text - text for parsing
     * @return parsed element of text
     */
    @Override
    public Component parse(final Component curTextElement, final String text) {
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            Composite sentence = new Composite(Category.SENTENCE);
            curTextElement.add(getParser().parse(sentence, matcher.group()),
                    Category.SENTENCE);
        }

        return curTextElement;
    }
}
