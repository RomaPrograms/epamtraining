package by.training.informhandling.entity;

import by.training.informhandling.parsing.parsingtext.ParseToParagraph;
import java.util.ArrayList;
import java.util.List;

public class Text implements TextTree {
    private List<TextTree> paragraphs = new ArrayList<>();
    private ParseToParagraph parseToParagraph;
    //и чем вообще отличается лексема от выражения и от слова

    public Text(String string) {
        parseToParagraph = new ParseToParagraph(string);
        paragraphs = parseToParagraph.parseCurrentText();
    }

    public List<TextTree> getParagraphs() {
        return paragraphs;
    }

    public void setParagraphs(List<TextTree> paragraphs) {
        this.paragraphs = paragraphs;
    }

    public void print() {
        for (var paragraph:paragraphs) {
            paragraph.print();
        }
    }
}
