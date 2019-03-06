package by.training.auction.validator;

import by.training.auction.programexception.ProgramException;

import java.util.List;

/**
 * validator which checks information in creator.
 */
public class CreatorValidator {
    /**
     * statement for incorrect line.
     */
    private static final String INCORRECT_LINE = "Incorrect line!";

    /**isRightParameters pinpoints is our parameters correct for cube.
     * @param arrayListDouble - string with parameters from file
     * @return true if we got valid string otherwise false
     */
    public boolean isRightParameters(final List<Double> arrayListDouble) {
        try {
            if (INCORRECT_LINE.isEmpty()) {
                throw new ProgramException();
            }
//            if (arrayListDouble.size() != NUMBER_OF_COORDINATES) {
//                throw new DataException(INCORRECT_LINE);
//            }
//
//            point1 = new Point(arrayListDouble.get(INDEX_OF_POINT_1_X),
//                    arrayListDouble.get(INDEX_OF_POINT_1_Y),
//                    arrayListDouble.get(INDEX_OF_POINT_1_Z));
//            point2 = new Point(arrayListDouble.get(INDEX_OF_POINT_2_X),
//                    arrayListDouble.get(INDEX_OF_POINT_2_Y),
//                    arrayListDouble.get(INDEX_OF_POINT_2_Z));
//            point3 = new Point(arrayListDouble.get(INDEX_OF_POINT_3_X),
//                    arrayListDouble.get(INDEX_OF_POINT_3_Y),
//                    arrayListDouble.get(INDEX_OF_POINT_3_Z));
//            if ((cubeAction.calculateSide(point1, point2)
//                    != cubeAction.calculateSide(point1, point3))
//                    && cubeAction.calculateSide(point1, point2)
//                    != cubeAction.calculateSide(point2, point3)) {
//                throw new DataException(INCORRECT_LINE);
//            }

        } catch (ProgramException ex) {
            return false;
        }
        return true;
    }
}
