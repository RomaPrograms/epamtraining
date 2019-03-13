package by.training.informhandling.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Text {
    private static final String REGULAR_EXPRESSION = "";
    Pattern pattern = Pattern.compile(REGULAR_EXPRESSION);

    private List<Paragraph> paragraphs = new ArrayList<>();
    //и чем вообще отличается лексема от выражения и от слова

    public Text(String string) {
        Matcher matcher = pattern.matcher(string);
        while(matcher.find()) {
            paragraphs.add(new Paragraph(string));
        }
    }

    public void print() {
        for (var paragraph:paragraphs) {
            paragraph.print();
        }
    }
}
