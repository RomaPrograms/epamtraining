package action.sortingaction;

import by.training.informhandling.actions.TextAction;
import by.training.informhandling.entity.Composit;
import by.training.informhandling.parsing.parsingtexttoelements.ParseText;
import by.training.informhandling.parsing.parsingtexttoelements.ParseToParagraph;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * class tests methods for sorting.
 */
public class SortingAction {
    /**
     * original text.
     */
    private static String wholeText;
    /**
     * text sorted by number of sentences.
     */
    private static String sortedTextByNumberOfSentences;
    /**
     * text sorted by length of words.
     */
    private static String sortedTextByLengthOfWords;
    /**
     * text sorted by number of entrance in lexemes.
     */
    private static String sortedLexemesByNumberOfEntrance;
    /**
     * first composit.
     */
    private Composit composit = new Composit();
    /**
     * second composit.
     */
    private Composit composit1 = new Composit();
    /**
     * object of ParseToParagraph class.
     */
    private ParseText parseToParagraph = new ParseToParagraph();
    /**
     * object of class with text action classes.
     */
    private TextAction actions = new TextAction();

    /**
     * method for initialization parameters before other methods.
     */
    @BeforeClass
    public void initialiseSortingAction() {
        wholeText = "    It has survived - not only (five) centuries, but also "
                + "the leap into 13<<2 electronic typesetting, remaining 30>>>3"
                + " essentially ~6&9|(3&4) unchanged... It was popularised in"
                + " the 5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1) with the release of"
                + " Letraset sheets containing Lorem Ipsum passages, and more"
                + " recently with desktop publishing software like Aldus"
                + " PageMarker including versions of Lorem Ipsum...    It is a"
                + " long established fact that a reader will be distracted by"
                + " the readable content of a page when looking at its layout."
                + " The point of using (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 "
                + "Ipsum is that it has a more-or-less normal distribution of"
                + " letters, as opposed to using (Content here), content here',"
                + " making it look like readable English.    It is a"
                + " (8^5|1&2<<(2|5>>2&71))|1200 established fact that a reader"
                + " will be of a page when looking at its layout.    Bye.";
        sortedTextByNumberOfSentences = "It is a 1200 established fact that a"
                + " reader will be of a page when looking at its layout. \n"
                + "Bye. \n"
                + "It has survived - not only (five) centuries, but also"
                + " the leap into 52 electronic typesetting, remaining 3"
                + " essentially 9 unchanged... It was popularised in the"
                + " 5 with the release of Letraset sheets containing Lorem"
                + " Ipsum passages, and more recently with desktop publishing"
                + " software like Aldus PageMarker including versions of"
                + " Lorem Ipsum... \n"
                + "It is a long established fact that a reader will be"
                + " distracted by the readable content of a page when"
                + " looking at its layout. The point of using 78 Ipsum is"
                + " that it has a more-or-less normal distribution of letters,"
                + " as opposed to using (Content here), content here', making"
                + " it look like readable English. \n";
        sortedTextByLengthOfWords = "- It has not but the only (five) also"
                + " leap into 52 3 survived centuries, remaining unchanged..."
                + " electronic 9 typesetting, essentially It in of of was the"
                + " the and with more with like Lorem Ipsum Aldus Lorem"
                + " Ipsum... sheets release desktop Letraset passages,"
                + " recently software versions including containing publishing"
                + " PageMarker popularised 5 \n"
                + "a a a It is be by of at the its long fact that will page"
                + " when reader layout. content looking readable distracted"
                + " established a of is it of as to it The has that"
                + " more-or-less here), here', look like point using Ipsum "
                + "using normal making letters, opposed (Content content "
                + "English. readable distribution 78 \n"
                + "a a a It is be of at its fact that will page when reader "
                + "layout. looking established 1200 \n"
                + "Bye. \n";
        sortedLexemesByNumberOfEntrance = "PageMarker passages, readable"
                + " readable Letraset a a a a a a a also and as at at"
                + " containing distracted essentially established established"
                + " fact fact has has layout. layout. leap making normal page"
                + " page popularised reader reader release remaining software"
                + " that that that unchanged... was"
                + "   (8^5|1&2<<(2|5>>2&71))|1200 (Content (five)"
                + " (~71&(2&3|(3|(2&1>>2|2)&2)|10&2))|78 - 13<<2 30>>>3"
                + " 5|(1&2&(3|(4&(25^5|6&47)|3)|2)|1) Aldus Bye. English."
                + " Ipsum Ipsum Ipsum... It It It It Lorem Lorem The be be"
                + " but by centuries, content content desktop distribution"
                + " electronic here', here), in including into is is is it"
                + " it its its letters, like like long look looking looking"
                + " more more-or-less not of of of of of of only opposed point"
                + " publishing recently sheets survived the the the the to"
                + " typesetting, using using versions when when will will"
                + " with with ~6&9|(3&4) ";
        parseToParagraph.parse(composit, wholeText);
        parseToParagraph.parse(composit1, wholeText);
    }

    /**
     * data for method with sorting by number of sentences.
     * @return - composit and expected result
     */
    @DataProvider(name = "dataProviderForSortingByNumberOfSentences")
    public Object[][] dataProviderForSortingByNumberOfSentences() {
        return new Object[][] {{composit, sortedTextByNumberOfSentences}};
    }

    /**
     * test for method with sorting by number of sentences.
     * @param composit - composit
     * @param sortedText - expected result
     */
    @Test(dataProvider = "dataProviderForSortingByNumberOfSentences")
    public void sortingByNumberOfSentencesAction(Composit composit,
                                             String sortedText) {
        actions.sortByNumberOfSentences(composit);
        String actual = composit.toString();
        String expected = sortedText;
        Assert.assertEquals(actual, expected);
    }

    /**
     * data for method with sorting by length of words.
     * @return - composit and expected result
     */
    @DataProvider(name = "dataProviderForSortingTextByLengthOfWords")
    public Object[][] dataProviderForSortingTextByLengthOfWords() {
        return new Object[][] {{composit1, sortedTextByLengthOfWords}};
    }

    /**
     * test for method with sorting by length of words.
     * @param composit - composit
     * @param sortedText - expected result
     */
    @Test(dataProvider = "dataProviderForSortingTextByLengthOfWords")
    public void sortingTextByLengthOfWordsAction(Composit composit,
                                                 String sortedText) {
        actions.sortWordsByLength(composit);
        String actual = composit.toString();
        String expected = sortedText;
        Assert.assertEquals(actual, expected);
    }

    /**
     * data for method with sorting lexemes by number of sentences.
     * @return - composit and expected result
     */
    @DataProvider(name = "dataProviderForSortingLexemesByNumberOfEntrance")
    public Object[][] dataProviderForSortingLexemesByNumberOfEntrance() {
        return new Object[][] {{composit1, sortedLexemesByNumberOfEntrance}};
    }

    /**
     * test for method with sorting lexemes by number of sentences.
     * @param composit - composit
     * @param sortedText - expected result
     */
    @Test(dataProvider = "dataProviderForSortingLexemesByNumberOfEntrance")
    public void sortingLexemesByNumberOfEntranceAction(Composit composit,
                                                 String sortedText) {
        String actual = actions.sortLexemesByEntranceOfSomeSymbol(composit,
                "a");
        String expected = sortedText;
        Assert.assertEquals(actual, expected);
    }
}
