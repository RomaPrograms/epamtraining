package by.training.informhandling.entity;

import by.training.informhandling.parsing.parsingtext.ParseToSymbol;
import java.util.List;

public class WordExpression implements TextTree {
    private List<TextTree> symbols;
    private ParseToSymbol parseToSymbol;

    public WordExpression(String string) {
        parseToSymbol = new ParseToSymbol(string);
        System.out.println(string);
        symbols = parseToSymbol.parseCurrentText();
    }

    public List<TextTree> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<TextTree> symbols) {
        this.symbols = symbols;
    }

    public void print() {
        for (var symbol:symbols) {
            symbol.print();
        }
    }
}
