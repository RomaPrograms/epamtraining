package action.parsingaction;

import by.training.informhandling.entity.Component;
import by.training.informhandling.entity.Composit;
import by.training.informhandling.parsing.parsingtext.ParseText;
import by.training.informhandling.parsing.parsingtext.ParseToParagraph;
import by.training.informhandling.parsing.parsingtext.ParseToLexeme;
import by.training.informhandling.parsing.parsingtext.ParseToSymbol;
import by.training.informhandling.parsing.parsingtext.ParseToSentence;
import by.training.informhandling.parsing.parsingtext.ParseToWordAndExpression;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ParsingTextToElementsAction {

    private static ParseText parseToParagraph = new ParseToParagraph();
    private static ParseText parseToSentence = new ParseToSentence();
    private static ParseText parseToLexeme = new ParseToLexeme();
    private static ParseText parseToWordAndExpression = new ParseToWordAndExpression();
    private static ParseText parseToSymbol = new ParseToSymbol();
    private String wholeText;
    private String paragraph1;
    private String paragraph2;
    private String sentence1;
    private String sentence2;
    private String lexeme1;
    private String lexeme2;
    private String word1;
    private String word2;

    @BeforeClass
    public void initialiseParsingTextToElementsAction() {
        wholeText = "    It has survived - not only (five) centuries, but also "
                + "the leap into 13<<2 electronic typesetting, remaining 30>>>3"
                + " essentially ~6&9|(3&4) unchanged. It was popularised in the"
                + " 5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1) with the release of"
                + " Letraset sheets containing Lorem Ipsum passages, and more"
                + " recently with desktop publishing software like Aldus"
                + " PageMarker including versions of Lorem Ipsum.    It is a"
                + " long established fact that a reader will be distracted by"
                + " the readable content of a page when looking at its layout."
                + " The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 "
                + "Ipsum is that it has a more-or-less normal distribution of"
                + " letters, as opposed to using (Content here), content here',"
                + " making it look like readable English.    It is a"
                + " (8^5|1&2<<(2|5>>2&71))|1200 established fact that a reader"
                + " will be of a page when looking at its layout.    Bye.";
        paragraph1 = "It has survived - not only (five) centuries, but also "
                + "the leap into 13<<2 electronic typesetting, remaining 30>>>3"
                + " essentially ~6&9|(3&4) unchanged. It was popularised in the"
                + " 5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1) with the release of"
                + " Letraset sheets containing Lorem Ipsum passages, and more"
                + " recently with desktop publishing software like Aldus"
                + " PageMarker including versions of Lorem Ipsum.";
        paragraph2 = "It is a"
                + " long established fact that a reader will be distracted by"
                + " the readable content of a page when looking at its layout."
                + " The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 "
                + "Ipsum is that it has a more-or-less normal distribution of"
                + " letters, as opposed to using (Content here), content here',"
                + " making it look like readable English.";
        sentence1 = "It has survived - not only (five) centuries, but also "
                + "the leap into 13<<2 electronic typesetting, remaining 30>>>3"
                + " essentially ~6&9|(3&4) unchanged.";
        sentence2 = "It was popularised in the"
                + " 5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1) with the release of"
                + " Letraset sheets containing Lorem Ipsum passages, and more"
                + " recently with desktop publishing software like Aldus"
                + " PageMarker including versions of Lorem Ipsum.";
        lexeme1 = "(five)";
        lexeme2 = "centuries,";
        word1 = "electronic";
        word2 = "~6&9|(3&4)";
    }

    @DataProvider(name = "dataProviderForParseToParagraphAction")
    public Object[] dataProviderForParseToParagraphAction() {
        return new Object[]{wholeText, 4};
    }

    @Test (dataProvider = "dataProviderForParseToParagraphAction")
    public void parseToParagraphAction(String text,
                                       int numberOfParagraphs) {
        Composit composit = new Composit();
        Component actual = parseToParagraph.parse(composit, text);
        int expected = numberOfParagraphs;
        Assert.assertEquals(actual.getComponents().size(), expected);
    }

    @DataProvider(name = "dataProviderForParseToSentenceAction")
    public Object[][] dataProviderForParseToSentenceAction() {
        return new Object[][]{{paragraph1, 2}, {paragraph2, 2}};
    }

    @Test (dataProvider = "dataProviderForParseToSentenceAction")
    public void parseToSentenceAction(String paragraph, int size) {
        Composit composit = new Composit();
        Component actual = parseToSentence.parse(composit, paragraph);
        int expected = size;
        Assert.assertEquals(actual.getComponents().size(), expected);
    }

    @DataProvider(name = "dataProviderForParseToLexemeAction")
    public Object[][] dataProviderForParseToLexemeAction() {
        return new Object[][]{{sentence1, 21}, {sentence2, 31}};
    }

    @Test (dataProvider = "dataProviderForParseToLexemeAction")
    public void parseToLexemeAction(String lexeme, int size) {
        Composit composit = new Composit();
        Component actual = parseToLexeme.parse(composit, lexeme);
        int expected = size;
        Assert.assertEquals(actual.getComponents().size(), expected);
    }

    @DataProvider(name = "dataProviderForParseToWordAndExpressionAction")
    public Object[][] dataProviderForParseToWordAndExpressionAction() {
        return new Object[][]{{lexeme1, 3}, {lexeme2, 2}};
    }

    @Test (dataProvider = "dataProviderForParseToWordAndExpressionAction")
    public void parseToWordAndExpressionAction(String word, int size) {
        Composit composit = new Composit();
        Component actual = parseToWordAndExpression.parse(composit, word);
        int expected = size;
        Assert.assertEquals(actual.getComponents().size(), expected);
    }

    @DataProvider(name = "dataProviderForParseToSymbolAction")
    public Object[][] dataProviderForParseToSymbolAction() {
        return new Object[][]{{word1, 10}, {word2, 10}};
    }

    @Test (dataProvider = "dataProviderForParseToSymbolAction")
    public void parseToSymbolAction(String symbol, int size) {
        Composit composit = new Composit();
        Component actual = parseToSymbol.parse(composit, symbol);
        int expected = size;
        Assert.assertEquals(actual.getComponents().size(), expected);
    }
}
