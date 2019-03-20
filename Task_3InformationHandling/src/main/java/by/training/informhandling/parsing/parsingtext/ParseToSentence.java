package by.training.informhandling.parsing.parsingtext;

import by.training.informhandling.entity.Category;
import by.training.informhandling.entity.Component;
import by.training.informhandling.entity.Composit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseToSentence extends ParseText {
    private static final String REGULAR_EXPRESSION = ".*?([!?]|[.]+)"; //(([^[!]]+[!])|([^[?]]+[?]))
    private Pattern pattern = Pattern.compile(REGULAR_EXPRESSION);

    public ParseToSentence() {
        parser = new ParseToLexeme();
    }

    @Override
    public Component parse(Composit curParser, String text) {
        Matcher matcher = pattern.matcher(text);

        while(matcher.find()) {
            Composit sentence = new Composit(Category.SENTENCE);
            curParser.add(parser.parse(sentence, matcher.group()),
                    Category.SENTENCE);
        }

        return curParser;
    }
}
