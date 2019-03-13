package by.training.informhandling.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Paragraph {
    private static final String REGULAR_EXPRESSION = "";
    Pattern pattern = Pattern.compile(REGULAR_EXPRESSION);

    private List<Sentence> sentences = new ArrayList<>();

    public Paragraph(String string) {
        Matcher matcher = pattern.matcher(string);
        while(matcher.find()) {
            sentences.add(new Sentence(string));
        }
    }

    public void print() {
        for (var sentence:sentences) {
            sentence.print();
        }
    }
}
