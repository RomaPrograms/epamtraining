/**
 * These package contain the class {@code CubeAction}.
 *
 * @since 1.0
 * @author Roman
 * @version 1.0
 */
package by.training.cube.action;

import by.training.cube.entity.Cube;
import by.training.cube.entity.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.training.cube.entity.Plane;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * CubeAction is class that contains methods which are work with cube.
 * @author Roman
 * @version 1.0
 * */
public class CubeAction {
    /**
     * number of all cube sides.
     */
    public static final int NUMBER_OF_CUBE_SIDES = 6;

    /**
     * count of numbers after comma.
     */
    public static final int COUNT_OF_ROUNDING_NUMBERS = 8;

    /**
     * degree for calculation Volume of cube.
     */
    public static final int POWER_3 = 3;

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER = LogManager.getLogger(CubeAction.class);

    /**
     * calculateSquare is method that calculate square of our cube.
     * @param cubeSide - side of cube
     * @return returns square
     */
    public double calculateSquare(final double cubeSide) {
        return Math.pow(cubeSide, 2) * NUMBER_OF_CUBE_SIDES;
    }

    /**
     * calculateVolume is method that calculate volume of our cube.
     * @param cubeSide - side of cube
     * @return returns volume
     */
    public double calculateVolume(final double cubeSide) {
        return Math.pow(cubeSide, POWER_3);
    }

    /**calculateSide is method that calculate side of our cube.
     * @param point1 - first point
     * @param point2 - second point
     * @return returns length of side
     * */
    public double calculateSide(final Point point1, final Point point2) {
        double side = Math.sqrt((Math.pow(point1.getX() - point2.getX(), 2)
                + Math.pow(point1.getY() - point2.getY(), 2)
                + Math.pow(point1.getZ() - point2.getZ(), 2))) / Math.sqrt(2);
        return new BigDecimal(side).setScale(COUNT_OF_ROUNDING_NUMBERS,
                RoundingMode.UP).doubleValue();
    }

    /**
     * calculateSegmentVolume is method that calculate segment volume of cube.
     * @param cubeSide - side of cube
     * @param height - height of cube
     * @return returns segment volume
     * */
    public double calculateSegmentVolume(final double cubeSide,
                                         final double height) {
        return cubeSide * cubeSide * height;
    }

    /**
     * calculateRationOfSegment is method that calculate ration of segments
     * in our cube.
     * @param cube - cube
     * @param plane - plane
     * @param point - point
     * @return returns ration of segments
     * */
    public double calculateRationOfSegment(final Cube cube, final Plane plane,
                                           final Point point) {
        double cubeSide = calculateSide(cube.getA(), cube.getB());
        Point cubeCenter = new Point(Math.abs(cube.getA().getX()
                - cube.getC().getX()) / 2, Math.abs(cube.getA().getY()
                - cube.getB().getY() / 2), Math.abs(cube.getA().getZ()
                - cube.getC().getZ()) / 2);
        double segmentVolume = 0;
        double pointCoordinate;

        switch (plane) {
            case XY:
                pointCoordinate = point.getZ();
                if ((pointCoordinate < (cubeCenter.getZ() + cubeSide / 2))
                        && (pointCoordinate
                        > (cubeCenter.getZ() - cubeSide / 2))) {
                    segmentVolume = calculateSegmentVolume(cubeSide,
                            point.getZ() - (cubeCenter.getZ()
                                    - cubeSide / 2));
                } else {
                    return 0.0;
                }
                break;
                case XZ:
                pointCoordinate = point.getY();
                if ((pointCoordinate < (cubeCenter.getY() + cubeSide / 2))
                        && (pointCoordinate
                        > (cubeCenter.getY() - cubeSide / 2))) {
                    segmentVolume = calculateSegmentVolume(cubeSide,
                            point.getY() - (cubeCenter.getY()
                                    - cubeSide / 2));
                } else {
                    return 0.0;
                }
                break;
                case YZ:
                pointCoordinate = point.getX();
                if ((pointCoordinate < (cubeCenter.getX() + cubeSide / 2))
                        && (pointCoordinate
                        > (cubeCenter.getX() - cubeSide / 2))) {
                    segmentVolume = calculateSegmentVolume(cubeSide,
                            point.getX()
                                    - (cubeCenter.getX() - cubeSide / 2));
                }
                else {
                    return 0.0;
                }
                break;
            default:
                return 0.0;
        }
        return (calculateVolume(cubeSide) - segmentVolume) / segmentVolume;
    }
    /**
     * isBaseOfCubeLiesOnPlane calculate does some side fo cube lay on some
     * plane.
     * @param cube - cube
     * @param point - point
     * @return returns true if some side of cube lies on some plane otherwise
     * false
     * */
    public boolean isBaseOfCubeLiesOnPlane(final Cube cube, final Point point) {
        double cubeSide = calculateSide(cube.getA(), cube.getB());
        Point cubeCenter = new Point(Math.abs(cube.getA().getX()
                - cube.getC().getX()) / 2, Math.abs(cube.getA().getY()
                - cube.getB().getY() / 2), Math.abs(cube.getA().getZ()
                - cube.getC().getZ()) / 2);
        if ((cubeCenter.getZ() + cubeSide / 2) == point.getZ()
                || (cubeCenter.getZ() + cubeSide / 2) == point.getZ()
                || (cubeCenter.getY() + cubeSide / 2) == point.getY()
                || (cubeCenter.getY() + cubeSide / 2) == point.getY()
                || (cubeCenter.getX() + cubeSide / 2) == point.getX()
                || (cubeCenter.getX() + cubeSide / 2) == point.getX()) {
            return true;
        } else {
            return false;
        }
    }
}
