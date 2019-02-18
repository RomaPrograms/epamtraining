/**
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

/**DataActionTest is class that tests all methods for working with data
 * @author Roman
 * @version 1.0
 */
public class DataActionTest {
    private Reader reader = new Reader();
    private Parser parser = new Parser();
    private ArrayList<String> arrayList;

    @BeforeClass
    public void initArrayList(){
        arrayList = new ArrayList<String>();
        arrayList.add("3.0 0.0 0.0 0.0 3.0 0.0 0.0 0.0 3.0");
        arrayList.add("4.0 0.0 0.0 0.0 4.0 0.0 0.0 0.0 4.0");
    }

    /**@dataProviderForReader return information to testReader method*/
    @DataProvider(name = "dataProviderForReader")
    public Object[] dataProviderForReader() {
        return new Object[]{"src\\data1.txt"};
    }

    /**@testReader tests method read from Reader class*/
    @Test(description = "test positive reading of cube data",
            dataProvider = "dataProviderForReader")
    public void testReader(String file) {
        ArrayList<String> actual = reader.readFromFile(file);
        ArrayList<String> expected = arrayList;
        Assert.assertEquals(actual, expected);
    }

    /**@dataProviderForParser return information to testParser method*/
    @DataProvider(name = "dataProviderForParser")
    public Object[] dataProviderForParser() {
        return new Object[]{arrayList};
    }

    /**@testParser tests method parser from Parser class*/
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

        ArrayList<ArrayList<Double>> actual = parser.parse(arrayListString);
        Assert.assertEquals(actual, expected);
    }
}
