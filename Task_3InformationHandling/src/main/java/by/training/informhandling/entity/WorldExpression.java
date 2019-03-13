package by.training.informhandling.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WorldExpression {
    private static final String REGULAR_EXPRESSION = "";
    Pattern pattern = Pattern.compile(REGULAR_EXPRESSION);

    private List<Symbol> symbols = new ArrayList<>();

    public WorldExpression(String string) {
        Matcher matcher = pattern.matcher(string);
        while(matcher.find()) {
            symbols.add(new Symbol(string));
        }
    }

    public void print() {
        for (var symbol:symbols) {
            symbol.print();
        }
    }
}
