package by.training.informhandling.entity;

import by.training.informhandling.parsing.ParseToSymbol;

import java.util.ArrayList;
import java.util.List;

public class WordExpression implements PrintTree {
    private List<Symbol> symbols = new ArrayList<>();
    private ParseToSymbol parseToSymbol;

    public WordExpression(String string) {
        parseToSymbol = new ParseToSymbol(string);
        symbols = parseToSymbol.parseCurrentText();
    }

    public void print() {
        for (var symbol:symbols) {
            symbol.print();
        }
    }
}
