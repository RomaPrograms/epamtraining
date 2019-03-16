package by.training.informhandling.parsing.parsingtext;

import by.training.informhandling.entity.Symbol;
import by.training.informhandling.entity.WordExpression;
import by.training.informhandling.parsing.ParsingChain;
import by.training.informhandling.parsing.parsingexpression.Calculator;
import by.training.informhandling.parsing.parsingexpression.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseToSymbol{
    private static final String REGULAR_EXPRESSION_FOR_LETTERS = "[a-zA-Z]";
    private static final String REGULAR_EXPRESSION_FOR_EXPRESIONS = "([\\d&~|^<>()][\\d&~|^<>()]+)";
    private Pattern patternForLetters;// = Pattern.compile(REGULAR_EXPRESSION);
    private ParsingChain parsingChain;
    private String text;

    public ParseToSymbol(String string) {
        text = string;
    }

    public List<Symbol> parseCurrentText() {
        List<Symbol> symbols = new ArrayList<>();
        Matcher matcher = Pattern.compile(REGULAR_EXPRESSION_FOR_LETTERS).matcher(text);
        if (matcher.find()) {
            do {
                symbols.add(new Symbol(matcher.group()));
            } while (matcher.find());
            return symbols;
        }
        Matcher matcher1 = Pattern.compile(REGULAR_EXPRESSION_FOR_EXPRESIONS).matcher(text);
        if (matcher1.find()) {
            String expression = Calculator.ExpressionToRPN(matcher1.group());
            Client interpreter = new Client(expression);
            symbols.add(new Symbol(String.valueOf(interpreter.calculate())));
            return symbols;
        }

        symbols.add(new Symbol(text));
        return symbols;
    }
}
