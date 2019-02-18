/**
 * These package contain the class {@code CubeActionTest}
 *
 * @since 1.0
 * @author Roman
 * @version 1.0
 */
package action;

import by.training.cube.action.CubeAction;
import by.training.cube.entity.Plane;
import by.training.cube.entity.Cube;
import by.training.cube.entity.Point;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static by.training.cube.entity.Plane.XY;

/**CubeActionTest is class, where we will test our some methods with
 * cube
 * @author Roman
 * @version 1.0
 */
public class CubeActionTest {

    private CubeAction cubeAction = new CubeAction();
    private Cube cube1;
    private Cube cube2;

    @BeforeClass
    public void initCube(){
        cube1 = new Cube(new Point(3.0, 0.0, 0.0),
                new Point(0.0, 3.0, 0.0),
                new Point(0.0, 0.0, 3.0));
        cube2 = new Cube(new Point(4.0, 0.0, 0.0),
                new Point(0.0, 4.0, 0.0),
                new Point(0.0, 0.0, 4.0));
    }

    /**
     * dataProviderForCalculateSquare transmits information to
     * testCalculateSquare method
     * @return returns to some methods cubes and supposed result of method
     */
    @DataProvider(name = "dataProviderForCalculateSquare")
    public Object[][] dataProviderForCalculateSquare(){
        return new Object[][]{ {cube1, 54}, {cube2, 96} };
    }

    /**
     * dataProviderForCalculateVolume return information
     * to testCalculateSquare Volume
     * @return returns to some methods cubes and supposed result of method
     */
    @DataProvider(name = "dataProviderForCalculateVolume")
    public Object[][] dataProviderForCalculateVolume(){
        return new Object[][]{ {cube1, 27}, {cube2, 64} };
    }

    /**
     * testCalculateSquare tests our method with cube "calculateSquare"*/
    @Test(description = "test positive calculation of cube square",
            dataProvider = "dataProviderForCalculateSquare")
    public void testCalculateSquare(Cube cube, double c){
        double actual = cubeAction.calculateSquare(
                cubeAction.calculateSide(cube.getA(), cube.getB()));
        double expected = c;
        Assert.assertEquals(actual, expected);
    }

    /**@testCalculateVolume tests our method with cube "calculateVolume"*/
    @Test(description = "test positive calculation of cube volume",
            dataProvider = "dataProviderForCalculateVolume")
    public void testCalculateVolume(Cube cube, double c){
        double actual = cubeAction.calculateVolume(
                cubeAction.calculateSide(cube.getA(), cube.getB()));
        double expected = c;
        Assert.assertEquals(actual, expected);
    }

    /**
     * dataProviderForCalculateVolume return information to
     * testCalculateSquare Volume
     * @return returns to some methods cubes, planes and points
     */
    @DataProvider()
    public Object[][] dataProviderForCalculateRationOfSegment()
            throws Exception{
        return new Object[][]{{cube1, XY,
                new Point(0.0, 0.0, 2.0), 0.5} };
    }

    /**@testCalculateRationOfSegment tests our method with
     * cube calculateRationOfSegment*/
    @Test(description = "test positive calculation ration of segments",
            dataProvider = "dataProviderForCalculateRationOfSegment")
    public void testCalculateRationOfSegment(Cube cube, Plane plane,
                                             Point point, double c){
        double actual = cubeAction.calculateRationOfSegment(cube, plane, point);
        double expected = c;
        Assert.assertEquals(actual, expected);
    }

    /**dataProviderForCalculateVolume return information to
     * testCalculateSquare Volume
     * @return returns to some methods cubes and points*/
    @DataProvider()
    public Object[][] dataProviderForIsBaseOfCubeLiesOnPlane() throws Exception{
        return new Object[][]{{cube1, new Point(0.0, 0.0, 3.0)}
        };
    }

    /**@testIsBaseOfCubeLiesOnPlane tests our method with cube
     * isBaseOfCubeLiesOnPlane*/
    @Test(description = "test positive is some side of cube lies on the plane",
            dataProvider = "dataProviderForIsBaseOfCubeLiesOnPlane")
    public void testIsBaseOfCubeLiesOnPlane(Cube cube, Point point) {
        boolean actual = cubeAction.isBaseOfCubeLiesOnPlane(cube, point);
        Assert.assertTrue(actual);
    }
}
