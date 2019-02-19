/*
 * These package contain the class {@code DataActionTest}
 *
 * @since 1.0
 * @author Roman
 * @version 1.0
 */
package action;

import by.training.cube.parser.Parser;
import by.training.cube.reader.Reader;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**DataActionTest is class that tests all methods for working with data.
 * @author Roman
 * @version 1.0
 */
public class DataActionTest {
    /**
     * object of class Reader.
     */
    private Reader reader = new Reader();
    /**
     * object of class Parser.
     */
    private Parser parser = new Parser();
    /**
     * arrayList of strings.
     */
    private ArrayList<String> arrayList;

    /**
     * data which initialised arrayList before testing.
     */
    @BeforeClass
    public void initArrayList(){
        arrayList = new ArrayList<String>();
        arrayList.add("3.0 0.0 0.0 0.0 3.0 0.0 0.0 0.0 3.0");
        arrayList.add("3.0 2.0 3.0 1.0 6.0 3.0 8.0 3.0 2.0");
        arrayList.add("4.0 0.0 0.0 0.0 4.0 0.0 0.0 0.0 4.0");
        arrayList.add("4.0 0.0 0.0 0.0 4.0z 0.0 0.0 0.0 4.0");
        arrayList.add("4.0 0.0 0.0 0.0 4.0 0.0 0.0 0.0");
        arrayList.add("-1.0 2.0 8.0 -3.0 4.0 8.0 -3.0 2.0 10.0");
    }

    /**dataProviderForReader return information to testReader method.
     * @return string with route to file*/
    @DataProvider(name = "dataProviderForReader")
    public Object[] dataProviderForReader() {
        return new Object[]{"src\\main\\resources\\data1.txt"};
    }

    /**testReader tests method read from Reader class.
     * @param file - file with data*/
    @Test(description = "test positive reading of cube data",
            dataProvider = "dataProviderForReader")
    public void testReader(String file) {
        ArrayList<String> actual = reader.readFromFile(file);
        ArrayList<String> expected = arrayList;
        Assert.assertEquals(actual, expected);
    }

    /**dataProviderForParser return information to testParser method
     * @return arrayList with strings*/
    @DataProvider(name = "dataProviderForParser")
    public Object[] dataProviderForParser() {
        return new Object[]{arrayList};
    }

    /**testParser tests method parser from Parser class
     * @param arrayListString - arrayList with strings*/
    @Test(description = "test positive parsing of data from 'reader'",
            dataProvider = "dataProviderForParser")
    public void testParser(ArrayList<String> arrayListString){
        ArrayList<ArrayList<Double>> expected = new ArrayList<ArrayList<Double>>();
        ArrayList<Double> arrayList = new ArrayList<Double>(Arrays.asList(3.0, 0.0,
                0.0, 0.0, 3.0, 0.0, 0.0, 0.0, 3.0));
        expected.add(arrayList);
        arrayList = new ArrayList<Double>(Arrays.asList(4.0, 0.0, 0.0, 0.0, 4.0,
                0.0, 0.0, 0.0, 4.0));
        expected.add(arrayList);
        arrayList = new ArrayList<Double>(Arrays.asList(-1.0, 2.0, 8.0, -3.0,
                4.0, 8.0, -3.0, 2.0, 10.0));
        expected.add(arrayList);


        ArrayList<ArrayList<Double>> actual = parser.parse(arrayListString);
        Assert.assertEquals(actual, expected);
    }
}
