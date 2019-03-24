package by.training.informhandling.parsing.parsingtexttoelements;

import by.training.informhandling.entity.Component;
import by.training.informhandling.entity.Composit;

/**
 * class that contains common methods and variables of all classes for parsing.
 */
public abstract class ParseText {
    /**
     * variable that contain some kind of parser for every classes for parsing.
     */
    private ParseText parser;

    /**
     * method returns object of current Parser class.
     * @return object of current Parser class
     */
    public ParseText getParser() {
        return parser;
    }

    /**
     * method sets object of current Parser class.
     * @param curParser - current Parser class
     */
    public void setParser(final ParseText curParser) {
        this.parser = curParser;
    }

    /**
     * methods where element of text will be parsed.
     * @param curTextElement - current element of text
     * @param text - text for parsing
     * @return parsed element of text
     */
    public abstract Component parse(Composit curTextElement, String text);
}
