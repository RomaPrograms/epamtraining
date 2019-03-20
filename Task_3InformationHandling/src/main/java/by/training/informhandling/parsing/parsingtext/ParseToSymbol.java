package by.training.informhandling.parsing.parsingtext;

import by.training.informhandling.entity.Category;
import by.training.informhandling.entity.Component;
import by.training.informhandling.entity.Composit;
import by.training.informhandling.entity.Leaf;

public class ParseToSymbol extends ParseText {
    private static final String REGULAR_EXPRESSION_FOR_LETTERS = "[a-zA-Z]";
    private static final String REGULAR_EXPRESSION_FOR_EXPRESIONS = "([\\d&~|^<>()][\\d&~|^<>()]+)";

    @Override
    public Component parse(Composit curParser, String text) {

//        Matcher matcher = Pattern.compile(REGULAR_EXPRESSION_FOR_LETTERS).matcher(text);
//        if (matcher.find()) {
//            do {
//                curParser.add(new Leaf(matcher.group()), Category.SYMBOL);
//                //System.out.print(matcher.group());
//            } while (matcher.find());
//            return curParser;
//        }
//
//        Matcher matcher1 = Pattern.compile(REGULAR_EXPRESSION_FOR_EXPRESIONS).matcher(text);
//        if (matcher1.find()) {
//            String expression = Calculator.ExpressionToRPN(matcher1.group());
//            Client interpreter = new Client(expression);
//            curParser.add(new Leaf(String.valueOf(interpreter.calculate())),
//                    Category.SYMBOL);
//            //System.out.print(matcher1.group());
//            return curParser;
//        }
//
//        //System.out.print(text);
//        curParser.add(new Leaf(text), Category.SYMBOL);
//        return curParser;

        ///////////////////////////////////////////////////////////////////////////////////////
        for (int i = 0; i < text.length(); i++) {
            curParser.add(new Leaf(String.valueOf(text.charAt(i))),
                    Category.SYMBOL);
        }
        return curParser;
    }
}
