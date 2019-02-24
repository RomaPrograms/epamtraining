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

import static by.training.cube.entity.Constants.INDEX_OF_POINT_1_X;
import static by.training.cube.entity.Constants.INDEX_OF_POINT_1_Y;
import static by.training.cube.entity.Constants.INDEX_OF_POINT_1_Z;
import static by.training.cube.entity.Constants.INDEX_OF_POINT_2_X;
import static by.training.cube.entity.Constants.INDEX_OF_POINT_2_Y;
import static by.training.cube.entity.Constants.INDEX_OF_POINT_2_Z;
import static by.training.cube.entity.Constants.INDEX_OF_POINT_3_X;
import static by.training.cube.entity.Constants.INDEX_OF_POINT_3_Y;
import static by.training.cube.entity.Constants.INDEX_OF_POINT_3_Z;

/**
 * Creator is class where we creates our cube if file have correct data.
 * @author Roman
 * @version 1.0
 */
public class Creator {

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
