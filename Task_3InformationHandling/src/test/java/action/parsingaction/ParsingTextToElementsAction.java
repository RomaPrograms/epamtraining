package action.parsingaction;

import by.training.informhandling.entity.Component;
import by.training.informhandling.entity.Composite;
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

/**
 * class with tests for parsing elements of text
 */
public class ParsingTextToElementsAction {
    /**
     * object of class for parsing text to paragraphs.
     */
    private static ParseText parseToParagraph = ParseToParagraph.getInstance();
    /**
     * object of class for parsing text to sentences.
     */
    private static ParseText parseToSentence = ParseToSentence.getInstance();
    /**
     * object of class for parsing text to lexemes.
     */
    private static ParseText parseToLexeme =ParseToLexeme.getInstance();
    /**
     * object of class for parsing text to words and expressions.
     */
    private static ParseText parseToWordAndExpression
            = ParseToWordAndExpression.getInstance();
    /**
     * object of class for parsing text to symbols.
     */
    private static ParseText parseToSymbol = ParseToSymbol.getInstance();
    /**
     * original text.
     */
    private String wholeText;
    /**
     * first paragraph.
     */
    private String paragraph1;
    /**
     * second paragraph.
     */
    private String paragraph2;
    /**
     * first sentence.
     */
    private String sentence1;
    /**
     * second sentence.
     */
    private String sentence2;
    /**
     * first lexeme.
     */
    private String lexeme1;
    /**
     * second lexeme.
     */
    private String lexeme2;
    /**
     * first word.
     */
    private String word1;
    /**
     * second word.
     */
    private String word2;

    /**
     * method for initialization parameters before other methods.
     */
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

    /**
     * data for getting information about text and number of paragraphs.
     * @return text and number of paragraphs
     */
    @DataProvider(name = "dataProviderForParseToParagraphAction")
    public Object[][] dataProviderForParseToParagraphAction() {
        return new Object[][] {{wholeText, 4}};
    }

    /**
     * method for testing parsing to paragraphs.
     * @param text - text
     * @param numberOfParagraphs - number of paragraphs after parsing
     */
    @Test (dataProvider = "dataProviderForParseToParagraphAction")
    public void parseToParagraphAction(String text,
                                       int numberOfParagraphs) {
        Composite composite = new Composite();
        Component actual = parseToParagraph.parse(composite, text);
        int expected = numberOfParagraphs;
        Assert.assertEquals(actual.getComponents().size(), expected);
    }

    /**
     * data for getting information about paragraphs and number of sentences.
     * @return paragraph and number of sentences
     */
    @DataProvider(name = "dataProviderForParseToSentenceAction")
    public Object[][] dataProviderForParseToSentenceAction() {
        return new Object[][]{{paragraph1, 2}, {paragraph2, 2}};
    }

    /**
     * method for testing parsing to sentences.
     * @param paragraph - paragraph
     * @param size - number of sentences after parsing
     */
    @Test (dataProvider = "dataProviderForParseToSentenceAction")
    public void parseToSentenceAction(String paragraph, int size) {
        Composite composite = new Composite();
        Component actual = parseToSentence.parse(composite, paragraph);
        int expected = size;
        Assert.assertEquals(actual.getComponents().size(), expected);
    }

    /**
     * data for getting information about sentences and number of lexemes.
     * @return sentences and number of lexemes
     */
    @DataProvider(name = "dataProviderForParseToLexemeAction")
    public Object[][] dataProviderForParseToLexemeAction() {
        return new Object[][]{{sentence1, 21}, {sentence2, 31}};
    }

    /**
     * method for testing parsing to lexemes.
     * @param sentence - sentence
     * @param size - number of lexemes after parsing
     */
    @Test (dataProvider = "dataProviderForParseToLexemeAction")
    public void parseToLexemeAction(String sentence, int size) {
        Composite composite = new Composite();
        Component actual = parseToLexeme.parse(composite, sentence);
        int expected = size;
        Assert.assertEquals(actual.getComponents().size(), expected);
    }

    /**
     * data for getting information about lexemes and number of words or
     * expressions.
     * @return lexemes and number of words or expressions
     */
    @DataProvider(name = "dataProviderForParseToWordAndExpressionAction")
    public Object[][] dataProviderForParseToWordAndExpressionAction() {
        return new Object[][]{{lexeme1, 3}, {lexeme2, 2}};
    }

    /**
     * method for testing parsing to words and expressions.
     * @param lexeme - lexeme
     * @param size - number of words and expressions after parsing
     */
    @Test (dataProvider = "dataProviderForParseToWordAndExpressionAction")
    public void parseToWordAndExpressionAction(String lexeme, int size) {
        Composite composite = new Composite();
        Component actual = parseToWordAndExpression.parse(composite, lexeme);
        int expected = size;
        Assert.assertEquals(actual.getComponents().size(), expected);
    }

    /**
     * data for getting information about words or expressions and number of
     * symbols.
     * @return words or expressions and number of symbols
     */
    @DataProvider(name = "dataProviderForParseToSymbolAction")
    public Object[][] dataProviderForParseToSymbolAction() {
        return new Object[][]{{word1, 10}, {word2, 10}};
    }

    /**
     * method for testing parsing to symbols.
     * @param word - word
     * @param size - number of symbols after parsing
     */
    @Test (dataProvider = "dataProviderForParseToSymbolAction")
    public void parseToSymbolAction(String word, int size) {
        Composite composite = new Composite();
        Component actual = parseToSymbol.parse(composite, word);
        int expected = size;
        Assert.assertEquals(actual.getComponents().size(), expected);
    }
}
