package by.training.cube.validator;

import by.training.cube.action.CubeAction;
import by.training.cube.entity.Point;
import by.training.cube.exceptions.DataException;

import java.util.List;


/**
 * validator which checks information in creator.
 */
public class CreatorValidator {

    /**
     * index of x in first point.
     */
    public static final int INDEX_OF_POINT_1_X = 0;
    /**
     * index of y in first point.
     */
    public static final int INDEX_OF_POINT_1_Y = 1;
    /**
     * index of z in first point.
     */
    public static final int INDEX_OF_POINT_1_Z = 2;
    /**
     * index of x in second point.
     */
    public static final int INDEX_OF_POINT_2_X = 3;
    /**
     * index of y in second point.
     */
    public static final int INDEX_OF_POINT_2_Y = 4;
    /**
     * index of z in second point.
     */
    public static final int INDEX_OF_POINT_2_Z = 5;
    /**
     * index of x in third point.
     */
    public static final int INDEX_OF_POINT_3_X = 6;
    /**
     * index of y in third point.
     */
    public static final int INDEX_OF_POINT_3_Y = 7;
    /**
     * index of z in third point.
     */
    public static final int INDEX_OF_POINT_3_Z = 8;
    /**
     * number of coordinates.
     */
    private static final int NUMBER_OF_COORDINATES = 9;
    /**
     * statement for incorrect line.
     */
    private static final String INCORRECT_LINE = "Incorrect line!";
    /**
     * Object of class {@code Cube Action} for doing some method with cube.
     */
    private CubeAction cubeAction = new CubeAction();

    /**isRightParameters pinpoints is our parameters correct for cube.
     * @param arrayListDouble - string with parameters from file
     * @return true if we got valid string otherwise false
     */
    public boolean isRightParameters(final List<Double> arrayListDouble) {
        try {
            Point point1;
            Point point2;
            Point point3;
            if (arrayListDouble.size() != NUMBER_OF_COORDINATES) {
                throw new DataException(INCORRECT_LINE);
            }

            point1 = new Point(arrayListDouble.get(INDEX_OF_POINT_1_X),
                    arrayListDouble.get(INDEX_OF_POINT_1_Y),
                    arrayListDouble.get(INDEX_OF_POINT_1_Z));
            point2 = new Point(arrayListDouble.get(INDEX_OF_POINT_2_X),
                    arrayListDouble.get(INDEX_OF_POINT_2_Y),
                    arrayListDouble.get(INDEX_OF_POINT_2_Z));
            point3 = new Point(arrayListDouble.get(INDEX_OF_POINT_3_X),
                    arrayListDouble.get(INDEX_OF_POINT_3_Y),
                    arrayListDouble.get(INDEX_OF_POINT_3_Z));
            if ((cubeAction.calculateSide(point1, point2)
                    != cubeAction.calculateSide(point1, point3))
                    && cubeAction.calculateSide(point1, point2)
                    != cubeAction.calculateSide(point2, point3)) {
                throw new DataException(INCORRECT_LINE);
            }

        } catch (DataException ex) {
            return false;
        }
        return true;
    }
}
