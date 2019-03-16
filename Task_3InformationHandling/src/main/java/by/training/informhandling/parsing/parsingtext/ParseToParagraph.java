package by.training.informhandling.parsing.parsingtext;

import by.training.informhandling.entity.Paragraph;
import by.training.informhandling.entity.TextTree;
import by.training.informhandling.parsing.ParsingChain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseToParagraph implements ParsingChain {
    private static final String REGULAR_EXPRESSION = ".{1}.*?\\s{4}";//.*\\s{4}.+\\s{4}
    private Pattern pattern = Pattern.compile(REGULAR_EXPRESSION, Pattern.MULTILINE);
    private String text;
    private int lastIndex;

    public ParseToParagraph(String string) {
        text = string;
    }

    public List<TextTree> parseCurrentText() {
        List<TextTree> paragraphs = new ArrayList<>();
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()) {
            lastIndex = matcher.end();
            paragraphs.add(new Paragraph(matcher.group()));
        }
        paragraphs.add(new Paragraph(text.substring(lastIndex)));
        return paragraphs;
    }
}
