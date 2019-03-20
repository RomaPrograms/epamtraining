package by.training.informhandling.parsing.parsingtext;

import by.training.informhandling.entity.Category;
import by.training.informhandling.entity.Component;
import by.training.informhandling.entity.Composit;
import java.util.regex.Pattern;

public class ParseToLexeme extends ParseText {
    private static final String REGULAR_EXPRESSION = ".+?[\\s.!?]+";  //решить проблему с Польской записью про которую мы говорили.
    private Pattern pattern = Pattern.compile(REGULAR_EXPRESSION);

    public ParseToLexeme() {
        parser = new ParseToWordAndExpression();
    }

    @Override
    public Component parse(Composit curParser, String text) {
//        Matcher matcher = pattern.matcher(text);
//        while(matcher.find()) {
//            Composit lexeme = new Composit();
//            curParser.add(parser.parse(lexeme, matcher.group()));
//        }

        String[] mass = text.split("\\s+");
        for (int i = 0; i < mass.length; i++) {
            Composit sentence = new Composit(Category.LEXEME);
            curParser.add(parser.parse(sentence, mass[i]), Category.LEXEME);
        }

        return curParser;
    }
}
