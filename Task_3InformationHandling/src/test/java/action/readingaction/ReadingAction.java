package action.readingaction;

import by.training.informhandling.reader.Reader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * class that tests reading from file.
 */
public class ReadingAction {
    /**
     * object of class which have a method for reading from file.
     */
    private static Reader reader = new Reader();
    /**
     * whole original text.
     */
    private String text;

    /**
     * method for initializing parameters of class before another methods.
     */
    @BeforeClass
    public void initialiseReadingAction() {
        text = "    It has survived - not only (five) centuries, but also "
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
    }

    /**
     * data provider for reading from file.
     * @return - name of file and text that it contains
     */
    @DataProvider(name = "dataProviderForReadingFromFileAction")
    public Object[][] dataProviderForReadingFromFileAction() {
        return new Object[][] {{"src\\main\\resources\\data\\data", text}};
    }

    /**
     * method which tests reading from file.
     * @param fileName - file name
     * @param text - text that we will expect
     */
    @Test(dataProvider = "dataProviderForReadingFromFileAction")
    public void readingFromFileAction(String fileName, String text) {
        String actual = reader.readFromFile(fileName);
        String expected = text;
        Assert.assertEquals(actual, expected);
    }
}
