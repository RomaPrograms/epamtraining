package by.training.informhandling.entity;

import by.training.informhandling.parsing.parsingtext.ParseToSentence;
import java.util.List;

public class Paragraph implements TextTree {
    private List<TextTree> sentences;
    private ParseToSentence parseToSentence;

    public Paragraph(String string) {
        System.out.println(string);
        parseToSentence = new ParseToSentence(string);
        sentences = parseToSentence.parseCurrentText();
    }

    public List<TextTree> getSentences() {
        return sentences;
    }

    public void setSentences(List<TextTree> sentences) {
        this.sentences = sentences;
    }

    public void print() {
        for (var sentence:sentences) {
            sentence.print();
        }
    }
}
