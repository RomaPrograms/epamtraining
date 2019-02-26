/*
 * These package contain the class {@code Creator}.
 *
 * @since 1.0
 * @author Roman
 * @version 1.0
 */
package by.training.cube.creator;

import by.training.cube.entity.Cube;
import by.training.cube.entity.Point;
import by.training.cube.validator.CreatorValidator;

import java.util.List;

/**
 * Creator is class where we creates our cube if file have correct data.
 * @author Roman
 * @version 1.0
 */
public class Creator {

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
     * object of class creatorValidator.
     */
    private CreatorValidator creatorValidator = new CreatorValidator();

    /**
     * create creates objects of class cube.
     * @param arrayList - ArrayList with data for cube points
     * @return returns cube
     */
    public Cube create(final List<Double> arrayList) {
        if (creatorValidator.isRightParameters(arrayList)) {
            return new Cube(new Point(arrayList.get(INDEX_OF_POINT_1_X),
                    arrayList.get(INDEX_OF_POINT_1_Y),
                    arrayList.get(INDEX_OF_POINT_1_Z)),
                    new Point(arrayList.get(INDEX_OF_POINT_2_X),
                            arrayList.get(INDEX_OF_POINT_2_Y),
                            arrayList.get(INDEX_OF_POINT_2_Z)),
                    new Point(arrayList.get(INDEX_OF_POINT_3_X),
                            arrayList.get(INDEX_OF_POINT_3_Y),
                            arrayList.get(INDEX_OF_POINT_3_Z)));
        }
        return null;
    }
}
