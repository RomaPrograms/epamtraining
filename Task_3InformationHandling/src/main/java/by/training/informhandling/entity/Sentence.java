package by.training.informhandling.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sentence {
    private static final String REGULAR_EXPRESSION = "";
    Pattern pattern = Pattern.compile(REGULAR_EXPRESSION);

    private List<Lexeme> lexemes = new ArrayList<>();

    public Sentence(String string) {
        Matcher matcher = pattern.matcher(string);
        while(matcher.find()) {
            lexemes.add(new Lexeme(string));
        }
    }

    public void print() {
        for (var lexem:lexemes) {
            lexem.print();
        }
    }
}
