package by.training.informhandling.entity;

import by.training.informhandling.parsing.parsingtext.ParseToParagraph;

import java.util.ArrayList;
import java.util.List;

public class Text implements PrintTree {
    private List<Paragraph> paragraphs = new ArrayList<>();
    private ParseToParagraph parseToParagraph;
    //и чем вообще отличается лексема от выражения и от слова

    public Text(String string) {
        parseToParagraph = new ParseToParagraph(string);
        paragraphs = parseToParagraph.parseCurrentText();
    }

    public void print() {
        for (var paragraph:paragraphs) {
            paragraph.print();
        }
    }
}
