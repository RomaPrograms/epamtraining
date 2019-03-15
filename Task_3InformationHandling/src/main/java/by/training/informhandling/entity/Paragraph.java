package by.training.informhandling.entity;

import by.training.informhandling.parsing.ParseToParagraph;
import by.training.informhandling.parsing.ParseToSentence;

import java.util.ArrayList;
import java.util.List;

public class Paragraph implements PrintTree {
    private List<Sentence> sentences = new ArrayList<>();
    private ParseToSentence parseToSentence;

    public Paragraph(String string) {
        System.out.println(string);
        parseToSentence = new ParseToSentence(string);
        sentences = parseToSentence.parseCurrentText();
    }

    public void print() {
        for (var sentence:sentences) {
            sentence.print();
        }
    }
}
