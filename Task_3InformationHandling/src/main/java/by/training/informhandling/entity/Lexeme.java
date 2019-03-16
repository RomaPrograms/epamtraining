package by.training.informhandling.entity;

import by.training.informhandling.parsing.parsingtext.ParseToWordAndExpression;
import java.util.List;

public class Lexeme implements TextTree {
    private List<TextTree> words;
    private ParseToWordAndExpression parseToWordAndExpression;

    public Lexeme(String string) {
        parseToWordAndExpression = new ParseToWordAndExpression(string);
        System.out.println(string);
        words = parseToWordAndExpression.parseCurrentText();
    }

    public List<TextTree> getWords() {
        return words;
    }

    public void setWords(List<TextTree> words) {
        this.words = words;
    }

    public void print() {
        for (var word:words) {
            word.print();
        }
    }
}
