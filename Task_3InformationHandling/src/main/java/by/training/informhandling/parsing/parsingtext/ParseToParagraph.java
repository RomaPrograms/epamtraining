package by.training.informhandling.parsing.parsingtext;

import by.training.informhandling.entity.Category;
import by.training.informhandling.entity.Component;
import by.training.informhandling.entity.Composit;

/**
 * class with all necessary for parsing text to Paragraphs.
 */
public class ParseToParagraph extends ParseText {
    /**
     * regular expression for parsing Text to Paragraphs.
     */
    private static final String REGULAR_EXPRESSION = "\\s{4}+";
//    private static ParseText parser;
    /**
     * constructor without parameters.
     */
    public ParseToParagraph() {
        if (getParser() == null) {
            setParser(new ParseToSentence());
        }
    }

//    private ParseToParagraph() {
//    }
//
//    public static synchronized ParseText getParser() {
//        if(parser == null) {
//            parser = ParseToSentence.getParser();
//        }
//        return parser;
//    }

    /**
     * methods where Text will be parsed to Paragraphs.
     * @param curTextElement - current element of text
     * @param text - text for parsing
     * @return parsed element of text
     */
    @Override
    public Component parse(final Composit curTextElement, final String text) {
        String[] sentences = text.split(REGULAR_EXPRESSION);

        for (int i = 1; i < sentences.length; i++) {
            Composit paragraph = new Composit(Category.PARAGRAPH);
            curTextElement.add(getParser().parse(paragraph, sentences[i]),
                    Category.PARAGRAPH);
        }

        return curTextElement;
    }
}
