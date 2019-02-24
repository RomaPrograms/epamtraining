/*
 * These package contain the class {@code CubeActionTest}
 *
 * @since 1.0
 * @author Roman
 * @version 1.0
 */
package action;

import by.training.cube.action.CubeAction;
import by.training.cube.creator.Creator;
import by.training.cube.entity.Cube;
import by.training.cube.entity.Point;
import by.training.cube.entity.Plane;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static by.training.cube.entity.Plane.XY;

/**CubeActionTest is class, where we will test our some methods with
 * cube.
 * @author Roman
 * @version 1.0
 */
public class CubeActionTest {

    /**
     * object of cubeAction class for testing it methods.
     */
    private CubeAction cubeAction = new CubeAction();
    /**
     * first cube for testing.
     */
    private Cube cube1;
    /**
     * second cube for testing.
     */
    private Cube cube2;

    /**
     * object of class Creator for creating Cubes.
     */
    private Creator creator = new Creator();
    /**
     * initialization parameters before testing and in addition tests Creator.
     */
    @BeforeClass
    public void initCube() {
        cube1 = creator.create( new ArrayList<>(Arrays.asList( 3.0, 0.0, 0.0,
                0.0, 3.0, 0.0, 0.0, 0.0, 3.0)));
        cube2 = creator.create( new ArrayList<>(Arrays.asList( 4.0, 0.0, 0.0,
                0.0, 4.0, 0.0,0.0, 0.0, 4.0)));
    }

    /**
     * dataProviderForCalculateSquare transmits information to
     * testCalculateSquare method.
     * @return returns to some methods cubes and supposed result of method
     */
    @DataProvider (name = "dataProviderForFalseCalculateSquare")
    public Object[][] dataProviderForFalseCalculateSquare() {
        return new Object[][]{ {cube1, 78}, {cube2, 26} };
    }

    /**
     * dataProviderForCalculateSquare transmits information to
     * testCalculateSquare method.
     * @return returns to some methods cubes and supposed result of method
     */
    @DataProvider (name = "dataProviderForTrueCalculateSquare")
    public Object[][] dataProviderForTrueCalculateSquare() {
        return new Object[][]{ {cube1, 54}, {cube2, 96} };
    }

    /**
     * dataProviderForCalculateVolume return information
     * to testCalculateSquare Volume.
     * @return returns to some methods cubes and supposed result of method
     */
    @DataProvider (name = "dataProviderForCalculateVolume")
    public Object[][] dataProviderForCalculateVolume() {
        return new Object[][]{ {cube1, 27}, {cube2, 64} };
    }

    /**
     * testCalculateSquare tests our method {@code calculateSquare}.
     * @param c - expected result
     * @param cube - cube
     */
    @Test (description = "test negative calculation of cube square",
            dataProvider = "dataProviderForFalseCalculateSquare")
    public void falseTestCalculateSquare(Cube cube, double c) {
        double actual = cubeAction.calculateSquare(
                cubeAction.calculateSide(cube.getA(), cube.getB()));
        double expected = c;
        Assert.assertEquals(actual, expected);
    }

    /**
     * testCalculateSquare tests our method {@code calculateSquare}.
     * @param c - expected result
     * @param cube - cube
     */
    @Test (description = "test positive calculation of cube square",
            dataProvider = "dataProviderForTrueCalculateSquare")
    public void trueTestCalculateSquare(Cube cube, double c) {
        double actual = cubeAction.calculateSquare(
                cubeAction.calculateSide(cube.getA(), cube.getB()));
        double expected = c;
        Assert.assertEquals(actual, expected);
    }

    /**Method tests our method with cube {@code calculateVolume}.
     * @param cube - cube
     * @param c - expected result*/
    @Test (description = "test positive calculation of cube volume",
            dataProvider = "dataProviderForCalculateVolume")
    public void testCalculateVolume(Cube cube, double c) {
        double actual = cubeAction.calculateVolume(
                cubeAction.calculateSide(cube.getA(), cube.getB()));
        double expected = c;
        Assert.assertEquals(actual, expected);
    }

    /**
     * {@code dataProviderForCalculateRationOfSegment} returns parameters.
     * @return returns to some methods cubes, planes and points
     */
    @DataProvider()
    public Object[][] dataProviderForCalculateRationOfSegment() {
        return new Object[][]{{cube1, XY,
                new Point(0.0, 0.0, 2.0), 0.5}};
    }

    /**testCalculateRationOfSegment tests our method with cube.
     * {@code calculateRationOfSegment}
     * @param c - expected value
     * @param cube - cube
     * @param point - point
     * @param plane - plane
     */
    @Test (description = "test positive calculation ration of segments",
            dataProvider = "dataProviderForCalculateRationOfSegment")
    public void testCalculateRationOfSegment(Cube cube, Plane plane,
                                             Point point, double c) {
        double actual = cubeAction.calculateRationOfSegment(cube, plane, point);
        double expected = c;
        Assert.assertEquals(actual, expected);
    }

    /**dataProviderForIsBaseOfCubeLiesOnPlane returns some parameters.
     * @return returns to some methods cubes and points*/
    @DataProvider ()
    public Object[][] dataProviderForIsBaseOfCubeLiesOnPlane() {
        return new Object[][]{{cube1, new Point(0.0, 0.0,
                3.0)}
        };
    }

    /**testIsBaseOfCubeLiesOnPlane tests our method with cube
     *{@code isBaseOfCubeLiesOnPlane}.
     * @param point - point
     * @param cube - cube
     */
    @Test (description = "test positive is some side of cube lies on the plane",
            dataProvider = "dataProviderForIsBaseOfCubeLiesOnPlane")
    public void testIsBaseOfCubeLiesOnPlane(Cube cube, Point point) {
        boolean actual = cubeAction.isBaseOfCubeLiesOnPlane(cube, point);
        Assert.assertTrue(actual);
    }
}
