package by.training.informhandling.entity;

import by.training.informhandling.parsing.ParseToLexeme;
import java.util.List;

public class Sentence implements PrintTree {
    private List<Lexeme> lexemes;
    private ParseToLexeme parseToLexeme;

    public Sentence(String string) {
        parseToLexeme = new ParseToLexeme(string);
        System.out.println(string);
        //lexemes = parseToLexeme.parseCurrentText();
    }


    public void print() {
        for (var lexeme:lexemes) {
            lexeme.print();
        }
    }
}
