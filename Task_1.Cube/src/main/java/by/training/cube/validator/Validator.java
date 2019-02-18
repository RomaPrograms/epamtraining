/**
 * These package contain the class {@code Validator}
 *
 * @since 1.0
 * @author Roman
 * @version 1.0
 */
package by.training.cube.validator;

import by.training.cube.action.CubeAction;
import by.training.cube.entity.Point;
import by.training.cube.exceptions.MyException;

import java.util.ArrayList;

/**
 * Validator is class that describes can we use our data for next work
 * with cube, or our data is incorrect.
 * @author Roman
 * @version 1.0
 */
public class Validator {
    /**
     * Object of class {@code Cube Action} for doing some method with cube.
     */
    private CubeAction cubeAction = new CubeAction();

    /**isRightParameters pinpoints is our parameters correct for cube.
     * @param number - number of line from file
     * @param string - string with data from file
     * @return true if we got valid string otherwise false
     */
    public boolean isRightParameters(final String string, final int number) {
        try {
            Point point1;
            Point point2;
            Point point3;

            String stringMassive[] = string.split("\\s");

            if (stringMassive.length != 9) {
                throw new MyException("We can't create cube from line " + number
                        + "cause it hasn't enough parameters");
            }

            ArrayList<Double> arrayListDouble = new ArrayList<Double>();
            for (int i = 0; i < stringMassive.length; i++) {
                arrayListDouble.add(Double.parseDouble(stringMassive[i]));
            }

            point1 = new Point(arrayListDouble.get(0), arrayListDouble.get(1),
                    arrayListDouble.get(2));
            point2 = new Point(arrayListDouble.get(3), arrayListDouble.get(4),
                    arrayListDouble.get(5));
            point3 = new Point(arrayListDouble.get(6), arrayListDouble.get(7),
                    arrayListDouble.get(8));

            if ((cubeAction.calculateSide(point1, point2)
                    != cubeAction.calculateSide(point1, point3))
                    && cubeAction.calculateSide(point1, point2)
                    != cubeAction.calculateSide(point2, point3)) {
                throw new MyException("We can't create cube from line " + number
                        + " cause parameters not for cube");
            }
        } catch (MyException ex) {
            return false;
        } catch (NumberFormatException ex) {
            new MyException("We can't create cube from line " + number
                    + " cause parameters incorrect");
            return false;
        }
        return true;
    }
}
