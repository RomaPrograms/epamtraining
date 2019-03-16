package by.training.informhandling.entity;

import by.training.informhandling.parsing.parsingtext.ParseToLexeme;
import by.training.informhandling.parsing.parsingtext.ParseToWordAndExpression;

import java.util.ArrayList;
import java.util.List;

public class Lexeme implements PrintTree{
    private List<WordExpression> words = new ArrayList<>();
    private ParseToWordAndExpression parseToWordAndExpression;

    public Lexeme(String string) {
        parseToWordAndExpression = new ParseToWordAndExpression(string);
        System.out.println(string);
        words = parseToWordAndExpression.parseCurrentText();
    }

    public void print() {
        for (var word:words) {
            word.print();
        }
    }
}
