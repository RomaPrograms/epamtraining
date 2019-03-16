package by.training.informhandling.parsing.parsingtext;

import by.training.informhandling.entity.Sentence;
import by.training.informhandling.entity.TextTree;
import by.training.informhandling.parsing.ParsingChain;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseToSentence implements ParsingChain {
    //private static final String REGULAR_EXPRESSION = "(.*[gd][.?!])*";
    private static final String REGULAR_EXPRESSION = "(.*?[!?.](\\s{4})?)"; //(([^[!]]+[!])|([^[?]]+[?]))
    private Pattern pattern = Pattern.compile(REGULAR_EXPRESSION);
    private String text;

    public ParseToSentence(String string) {
        text = string;
    }

    public List<TextTree> parseCurrentText() {
        List<TextTree> sentences = new ArrayList<>();
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()) {
            sentences.add(new Sentence(matcher.group()));
        }
        return sentences;
    }
}
