package by.training.informhandling.entity;

import by.training.informhandling.parsing.parsingtext.ParseToLexeme;
import java.util.List;

public class Sentence implements TextTree {
    private List<TextTree> lexemes;
    private ParseToLexeme parseToLexeme;

    public Sentence(String string) {
        parseToLexeme = new ParseToLexeme(string);
        System.out.println(string);
        lexemes = parseToLexeme.parseCurrentText();
    }

    public List<TextTree> getLexemes() {
        return lexemes;
    }

    public void setLexemes(List<TextTree> lexemes) {
        this.lexemes = lexemes;
    }

    public void print() {
        for (var lexeme:lexemes) {
            lexeme.print();
        }
    }
}
