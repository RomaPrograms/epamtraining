package by.training.informhandling.parsing.parsingtext;

import by.training.informhandling.entity.Symbol;
import by.training.informhandling.entity.WordExpression;
import by.training.informhandling.parsing.ParsingChain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseToSymbol{
    private static final String REGULAR_EXPRESSION = "";
    private Pattern pattern = Pattern.compile(REGULAR_EXPRESSION);
    private ParsingChain parsingChain;
    private String text;

    public ParseToSymbol(String string) {
        text = string;
    }

    public List<Symbol> parseCurrentText() {
        List<Symbol> symbols = new ArrayList<>();
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()) {
            symbols.add(new Symbol(matcher.group()));
        }
        return symbols;
    }
}
