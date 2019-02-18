/**
 * These package contain the class {@code Parser}
 *
 * @since 1.0
 * @author Roman
 * @version 1.0
 */
package by.training.cube.parser;

import by.training.cube.validator.Validator;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**Parses our ArrayList<String>.
 * @author Roman
 * @version 1.0
 */
public class Parser {

    /**
     * arrayList of doubles with coordinates.
     */
    private ArrayList<ArrayList<Double>> arrayListDoubles
            = new ArrayList<ArrayList<Double>>();
    /**
     * validator for checking valid information.
     */
    private Validator validator = new Validator();

    /**parse parses our data from String to Double.
     * @param arrayListStrings - arrayList of strings with points coordinates
     * @return arrayLists of doubles with point coordinates*/
    public ArrayList<ArrayList<Double>> parse(final ArrayList<String>
                                                       arrayListStrings) {
            for (int i = 0; i < arrayListStrings.size(); i++) {
                if (validator.isRightParameters(arrayListStrings.get(i), i)) {
                    ArrayList<Double> arrayList = new ArrayList<Double>();
                    StringTokenizer stringTokenizer = new StringTokenizer(
                            arrayListStrings.get(i), " ");
                    while (stringTokenizer.hasMoreTokens()) {
                        arrayList.add(Double.parseDouble(
                                stringTokenizer.nextToken()));
                    }
                    arrayListDoubles.add(arrayList);
                }
            }
        return arrayListDoubles;
    }
}
