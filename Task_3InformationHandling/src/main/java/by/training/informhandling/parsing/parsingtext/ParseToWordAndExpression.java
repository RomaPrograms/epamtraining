package by.training.informhandling.parsing.parsingtext;

import by.training.informhandling.entity.Category;
import by.training.informhandling.entity.Component;
import by.training.informhandling.entity.Composit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseToWordAndExpression extends ParseText {
    private static final String REGULAR_EXPRESSION = "([A-Za-z]+)|([\\d&~|^<>()]+)|(\\s)+|\\W";
    private Pattern pattern = Pattern.compile(REGULAR_EXPRESSION);

    public ParseToWordAndExpression() {
        parser = new ParseToSymbol();
    }

    @Override
    public Component parse(Composit curParser, String text) {
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()) {
            Composit wordAndExpression = new Composit(Category.WORDANDEXPRESSION);
            curParser.add(parser.parse(wordAndExpression, matcher.group()),
                    Category.WORDANDEXPRESSION);
        }
        return curParser;
    }
}
