package by.training.informhandling.entity;

import by.training.informhandling.parsing.parsingtext.ParseToLexeme;

import java.util.ArrayList;
import java.util.List;

public class Lexeme implements PrintTree{
    private List<WordExpression> words = new ArrayList<>();
    private ParseToLexeme parseToLexeme;

    public Lexeme(String string) {
        parseToLexeme = new ParseToLexeme(string);
        parseToLexeme.parseCurrentText();
    }

    public void print() {
        for (var word:words) {
            word.print();
        }
    }
}
