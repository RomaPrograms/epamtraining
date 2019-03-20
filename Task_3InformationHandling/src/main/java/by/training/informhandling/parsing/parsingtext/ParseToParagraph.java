package by.training.informhandling.parsing.parsingtext;

import by.training.informhandling.entity.Category;
import by.training.informhandling.entity.Component;
import by.training.informhandling.entity.Composit;

public class ParseToParagraph extends ParseText {
    private static final String REGULAR_EXPRESSION = "\\s{4}+";

    public ParseToParagraph() {
        parser = new ParseToSentence();
    }//Изменить, чтобы это не создавалось для каждого объекта

    @Override
    public Component parse(Composit curParser, String text) {
        String[] sentences = text.split(REGULAR_EXPRESSION);

        for (int i = 1; i < sentences.length; i++) {
            Composit paragraph = new Composit(Category.PARAGRAPH);
            curParser.add(parser.parse(paragraph, sentences[i]),
                    Category.PARAGRAPH);
        }

        return curParser;
    }
}
