package by.training.informhandling.parsing.parsingtext;

import by.training.informhandling.entity.Lexeme;
import by.training.informhandling.parsing.ParsingChain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseToLexeme{
    private static final String REGULAR_EXPRESSION = ".+?[\\s.!?]+";
    private Pattern pattern = Pattern.compile(REGULAR_EXPRESSION);
    private ParsingChain parsingChain;
    private String text;

    public ParseToLexeme(String string) {
        text = string;
    }

    public List<Lexeme> parseCurrentText() {
        List<Lexeme> paragraphs = new ArrayList<>();
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()) {
            paragraphs.add(new Lexeme(matcher.group()));
        }
        return paragraphs;
    }
}
