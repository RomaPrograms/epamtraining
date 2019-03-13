package by.training.informhandling.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexeme {
    private static final String REGULAR_EXPRESSION = "";
    Pattern pattern = Pattern.compile(REGULAR_EXPRESSION);

    private List<WorldExpression> words = new ArrayList<>();

    public Lexeme(String string) {
        Matcher matcher = pattern.matcher(string);
        while(matcher.find()) {
            words.add(new WorldExpression(string));
        }
    }

    public void print() {
        for (var word:words) {
            word.print();
        }
    }
}
