package by.training.cube.action;

import by.training.cube.entity.Cube;
import by.training.cube.entity.Point;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CubeAction {
    /**
     * CubeAction is class which contains methods which are work with cube.
     */

    public enum Plane {
        XY,
        XZ,
        YZ
    }

    private static final Logger LOGGER = LogManager.getLogger(CubeAction.class);

    public double calculateSquare(double cubeSide) {
        /**calculateSquare is method that calculate square of our cube.*/
        return Math.pow(cubeSide, 2) * 6;
    }

    public double calculateVolume(double cubeSide) {
        /**calculateVolume is method that calculate volume of our cube.*/
        return Math.pow(cubeSide, 3);
    }

    public double calculateSide(Point point1, Point point2){
        /**calculateSide is method that calculate side of our cube.*/
        double side = Math.sqrt((Math.pow(point1.getX() - point2.getX(), 2) + Math.pow(point1.getY() -
                point2.getY(), 2) + Math.pow(point1.getZ() - point2.getZ(), 2)))/Math.sqrt(2);
        return new BigDecimal(side).setScale(8, RoundingMode.UP).doubleValue();
    }

    public double calculateSegmentVolume(double cubeSide, double height) {
        /**calculateSegmentVolume is method that calculate segment volume of cube*/
        return cubeSide * cubeSide * height;
    }

    public double calculateRationOfSegment(Cube cube, Plane plane, Point point) {
        /**calculateRationOfSegment is method that calculate ration of segments in our cube*/

        double cubeSide = calculateSide(cube.getA(), cube.getB());
        Point cubeCenter = new Point(Math.abs(cube.getA().getX() - cube.getC().getX())/2, Math.abs(cube.getA().getY()
                - cube.getB().getY()/2), Math.abs(cube.getA().getZ() - cube.getC().getZ())/2);
        double segmentVolume = 0;
        double pointCoordinate;

        switch (plane) {
            case XY: {
                pointCoordinate = point.getZ();
                if ((pointCoordinate < (cubeCenter.getZ() + cubeSide / 2)) && (pointCoordinate >
                        (cubeCenter.getZ() - cubeSide / 2))) {

                    segmentVolume = calculateSegmentVolume(cubeSide, point.getZ() -
                            (cubeCenter.getZ() - cubeSide / 2));
                } else {
                    return 0.0;
                }
                break;
            }
            case XZ: {
                pointCoordinate = point.getY();
                if ((pointCoordinate < (cubeCenter.getY() + cubeSide / 2)) && (pointCoordinate >
                        (cubeCenter.getY() - cubeSide / 2))) {

                    segmentVolume = calculateSegmentVolume(cubeSide, point.getY() -
                            (cubeCenter.getY() - cubeSide / 2));
                } else {
                    return 0.0;
                }
                break;
            }
            case YZ: {
                pointCoordinate = point.getX();
                if ((pointCoordinate < (cubeCenter.getX() + cubeSide / 2)) && (pointCoordinate >
                        (cubeCenter.getX() - cubeSide / 2))) {

                    segmentVolume = calculateSegmentVolume(cubeSide, point.getX() -
                            (cubeCenter.getX() - cubeSide / 2));
                }else {
                    return 0.0;
                }
                break;
            }
        }
        return (calculateVolume(cubeSide) - segmentVolume) / segmentVolume;
    }

    public boolean isBaseOfCubeLiesOnPlane(Cube cube, Point point) {
        /**Method isBaseOfCubeLiesOnPlane calculate does some side fo cube lay on some plane*/
        double cubeSide = calculateSide(cube.getA(), cube.getB());
        Point cubeCenter = new Point(Math.abs(cube.getA().getX() - cube.getC().getX()) / 2, Math.abs(
                cube.getA().getY() - cube.getB().getY() / 2), Math.abs(cube.getA().getZ() - cube.getC().getZ()) / 2);

        if ((cubeCenter.getZ() + cubeSide / 2) == point.getZ() || (cubeCenter.getZ() + cubeSide / 2) == point.getZ() ||
                (cubeCenter.getY() + cubeSide / 2) == point.getY() || (cubeCenter.getY() + cubeSide / 2) == point.getY()
                || (cubeCenter.getX() + cubeSide / 2) == point.getX() ||
                (cubeCenter.getX() + cubeSide / 2) == point.getX()) {

            return true;
        } else {
            return false;
        }
        /*если лежит на XY*/
        /*если лежит на XZ*/
        /*если лежит на YZ*/
    }
}
