package by.training.informhandling.parsing.parsingtext;
import by.training.informhandling.entity.WordExpression;
import by.training.informhandling.parsing.ParsingChain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseToWordAndExpression {
    private static final String REGULAR_EXPRESSION = "([A-Za-z]+)|([\\d&~|^<>()]+)|(\\s)+|\\W";
    private Pattern pattern = Pattern.compile(REGULAR_EXPRESSION);
    private ParsingChain parsingChain;
    private String text;

    public ParseToWordAndExpression(String string) {
        text = string;
    }

    public List<WordExpression> parseCurrentText() {
        List<WordExpression> paragraphs = new ArrayList<>();
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()) {
            paragraphs.add(new WordExpression(matcher.group()));
        }
        return paragraphs;
    }
}
