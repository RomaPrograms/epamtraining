package by.training.informhandling.parsing.parsingtext;

import by.training.informhandling.entity.Paragraph;
import by.training.informhandling.parsing.ParsingChain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseToParagraph{
    private static final String REGULAR_EXPRESSION = "";//.*\\s{4}.+\\s{4}
    private Pattern pattern = Pattern.compile(REGULAR_EXPRESSION);
    private ParsingChain parsingChain;
    private String text;

    public ParseToParagraph(String string) {
        text = string;
    }

    public List<Paragraph> parseCurrentText() {
        List<Paragraph> paragraphs = new ArrayList<>();
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()) {
            paragraphs.add(new Paragraph(matcher.group()));
        }
        return paragraphs;
    }
}
