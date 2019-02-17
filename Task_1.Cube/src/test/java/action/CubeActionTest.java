package action;

import by.training.cube.action.CubeAction;
import by.training.cube.entity.Cube;
import by.training.cube.entity.Point;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CubeActionTest {
    /**CubeActionTest is class, where we will test our some methods with cube*/
    private CubeAction cubeAction = new CubeAction();
    private Cube cube1;
    private Cube cube2;

    @BeforeClass
    public void initCube(){
        cube1 = new Cube(new Point(3.0, 0.0, 0.0), new Point(0.0, 3.0, 0.0),
                new Point(0.0, 0.0, 3.0));
        cube2 = new Cube(new Point(4.0, 0.0, 0.0), new Point(0.0, 4.0, 0.0),
                new Point(0.0, 0.0, 4.0));
    }

    @DataProvider(name = "dataProviderForCalculateSquare")
    public Object[][] dataProviderForCalculateSquare(){
        /**dataProviderForCalculateSquare return information to testCalculateSquare method*/
        return new Object[][]{ {cube1, 54}, {cube2, 96} };
    }

    @DataProvider(name = "dataProviderForCalculateVolume")
    public Object[][] dataProviderForCalculateVolume(){
        /**dataProviderForCalculateVolume return information to testCalculateSquare Volume*/
        return new Object[][]{ {cube1, 27}, {cube2, 64} };
    }

    @Test(description = "test positive calculation of cube square", dataProvider = "dataProviderForCalculateSquare")
    public void testCalculateSquare(Cube cube, double c){
        /**testCalculateSquare tests our method with cube "calculateSquare"*/
        double actual = cubeAction.calculateSquare(cubeAction.calculateSide(cube.getA(), cube.getB()));
        double expected = c;
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "test positive calculation of cube volume", dataProvider = "dataProviderForCalculateVolume")
    public void testCalculateVolume(Cube cube, double c){
        /**testCalculateVolume tests our method with cube "calculateVolume"*/
        double actual = cubeAction.calculateVolume(cubeAction.calculateSide(cube.getA(), cube.getB()));
        double expected = c;
        Assert.assertEquals(actual, expected);
    }

    @DataProvider()
    public Object[][] dataProviderForCalculateRationOfSegment() throws Exception{
        /**dataProviderForCalculateVolume return information to testCalculateSquare Volume*/
        return new Object[][]{{cube1, CubeAction.Plane.XY, new Point(0.0, 0.0, 2.0), 0.5} };
    }

    @Test(description = "test positive calculation ration of segments",
            dataProvider = "dataProviderForCalculateRationOfSegment")
    public void testCalculateRationOfSegment(Cube cube, CubeAction.Plane plane, Point point, double c){
        /**testCalculateRationOfSegment tests our method with cube calculateRationOfSegment*/
        double actual = cubeAction.calculateRationOfSegment(cube, plane, point);
        double expected = c;
        Assert.assertEquals(actual, expected);
    }

    @DataProvider()
    public Object[][] dataProviderForIsBaseOfCubeLiesOnPlane() throws Exception{
        /**dataProviderForCalculateVolume return information to testCalculateSquare Volume*/
        return new Object[][]{{cube1, new Point(0.0, 0.0, 3.0)}
        };
    }

    @Test(description = "test positive is some side of cube lies on the plane",
            dataProvider = "dataProviderForIsBaseOfCubeLiesOnPlane")
    public void testIsBaseOfCubeLiesOnPlane(Cube cube, Point point) {
        /**testIsBaseOfCubeLiesOnPlane tests our method with cube isBaseOfCubeLiesOnPlane*/
        boolean actual = cubeAction.isBaseOfCubeLiesOnPlane(cube, point);
        Assert.assertTrue(actual);
    }
}
