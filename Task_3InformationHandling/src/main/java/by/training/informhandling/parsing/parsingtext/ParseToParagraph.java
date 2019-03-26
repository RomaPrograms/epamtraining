package by.training.informhandling.parsing.parsingtext;

import by.training.informhandling.entity.Category;
import by.training.informhandling.entity.Component;
import by.training.informhandling.entity.Composite;

/**
 * class with all necessary for parsing text to Paragraphs.
 */
public final class ParseToParagraph extends ParseText {
    /**
     * regular expression for parsing Text to Paragraphs.
     */
    private static final String REGULAR_EXPRESSION = "\\s{4}+";
    /**
     * constant object of class ParseToParagraph.
     */
    private static final ParseToParagraph INSTANCE = new ParseToParagraph();

    /**
     * returns object of current class for realization Singleton.
     * @return - object of class ParseToParagraph
     */
    public static ParseToParagraph getInstance() {
        return INSTANCE;
    }

    /**
     * sets next parser for realization Chain Of Responsibility.
     */
    private ParseToParagraph() {
        setParser(ParseToSentence.getInstance());
    }

    /**
     * methods where Text will be parsed to Paragraphs.
     * @param curTextElement - current element of text
     * @param text - text for parsing
     * @return parsed element of text
     */
    @Override
    public Component parse(final Component curTextElement, final String text) {
        String[] sentences = text.split(REGULAR_EXPRESSION);

        for (int i = 1; i < sentences.length; i++) {
            Composite paragraph = new Composite(Category.PARAGRAPH);
            curTextElement.add(getParser().parse(paragraph, sentences[i]),
                    Category.PARAGRAPH);
        }

        return curTextElement;
    }
}
