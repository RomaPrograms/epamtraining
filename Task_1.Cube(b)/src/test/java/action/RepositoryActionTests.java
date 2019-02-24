package action;

import by.training.cube.creator.Creator;
import by.training.cube.entity.Cube;
import by.training.cube.entity.CubeData;
import by.training.cube.entity.Parameter;
import by.training.cube.entity.Point;
import by.training.cube.repository.CubeRepository;
import by.training.cube.repository.Repository;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static by.training.cube.entity.Parameter.SQUARE;
import static by.training.cube.entity.Parameter.ID;
import static by.training.cube.entity.Parameter.NAME;
import static by.training.cube.entity.Parameter.VOLUME;

/**
 * class tests all methods in repository.
 */
public class RepositoryActionTests {

    /**
     * first cube for tests.
     */
    private Cube cube1;
    /**
     * second cube for tests.
     */
    private Cube cube2;
    /**
     * third cube for tests.
     */
    private Cube cube3;
    /**
     * object of class Repository for testing.
     */
    private Repository repository;
    /**
     * list with coordinates for cube.
     */
    private List<Double> newCoordinates;

    /**
     * object of class Creator for creating Cubes.
     */
    private Creator creator = new Creator();
    /**
     * initialization parameters before testing and testing class creator.
     */
    @BeforeClass
    public void initCube() {
        cube1 = creator.create( new ArrayList<>(Arrays.asList( 3.0, 0.0, 0.0,
                0.0, 3.0, 0.0, 0.0, 0.0, 3.0)));
        cube2 = creator.create( new ArrayList<>(Arrays.asList( 4.0, 0.0, 0.0,
                0.0, 4.0, 0.0,0.0, 0.0, 4.0)));
        cube3 = creator.create( new ArrayList<>(Arrays.asList( -1.0, 2.0, 8.0,
                -3.0, 4.0, 8.0, -3.0, 2.0, 10.0)));

        newCoordinates = new ArrayList<>(Arrays.asList(5.0, 0.0, 0.0, 0.0,
                5.0, 0.0, 0.0, 0.0, 5.0));

        repository = CubeRepository.getRepository();
        repository.add(cube1, "1", "cube_1");
        repository.add(cube2, "2", "cube_2");
        repository.add(cube3, "3", "cube_3");
    }

    /**
     * data for testing testFindingByNameAndId method.
     * @return - repository, parameter and string
     */
    @DataProvider()
    public Object[][] dataProviderForFindingByNameAndId() {
        return new Object[][] { {repository, NAME, "cube_2"},
                {repository, ID, "2"},
        };
    }

    /**
     * tests finding cubes by name and id in repository.
     * @param repository - repository
     * @param parameter - parameter
     * @param name - information about parameter
     */
    @Test(description = "test positive",
            dataProvider = "dataProviderForFindingByNameAndId")
    public void testFindingByNameAndId(CubeRepository repository,
                                       Parameter parameter, String name) {
        List<Cube> actual = repository.find(parameter, name);
        List<Cube> expected = new ArrayList<>();
        expected.add(cube2);
        Assert.assertEquals(actual, expected);
    }

    /**
     * data for testing testFindingByVolumeAndSquare method.
     * @return - repository, parameter and double
     */
    @DataProvider()
    public Object[][] dataProviderForFindingByVolumeAndSquare() {
        return new Object[][]{{repository, VOLUME, 64},
                {repository, SQUARE, 96},
        };
    }

    /**
     * tests finding cubes by volume and square in repository.
     * @param repository - repository
     * @param parameter - parameter
     * @param information - information about parameter
     */
    @Test(description = "test positive",
            dataProvider = "dataProviderForFindingByVolumeAndSquare")
    public void testFindingByVolumeAndSquare(CubeRepository repository,
                                             Parameter parameter,
                                             double information) {
        List<Cube> actual = repository.find(parameter, information);
        List<Cube> expected = new ArrayList<>();
        expected.add(cube2);
        Assert.assertEquals(actual, expected);
    }

    /**
     * data for testSortingByVolume method.
     * @return returns repository
     */
    @DataProvider()
    public Object[][] dataProviderForSortingByVolume() {
        return new Object[][]{{repository, VOLUME}
        };
    }

    @Test(description = "test positive",
            dataProvider = "dataProviderForSortingByVolume")
    public void testSortingByVolume(CubeRepository repository,
                                    Parameter parameter) {
        List<Cube> actual = repository.sort(parameter);
        List<Cube> expected = new ArrayList<>();
        expected.add(cube3);
        expected.add(cube1);
        expected.add(cube2);
        Assert.assertEquals(actual, expected);
    }

    @DataProvider()
    public Object[][] dataProviderForSortingByName() {
        return new Object[][]{{repository, NAME}
        };
    }

    @Test(description = "test positive",
            dataProvider = "dataProviderForSortingByName")
    public void testSortingByName(CubeRepository repository,
                                  Parameter parameter) {
        List<Cube> actual = repository.sort(parameter);
        List<Cube> expected = new ArrayList<>();
        expected.add(cube1);
        expected.add(cube2);
        expected.add(cube3);
        Assert.assertEquals(actual, expected);
    }

    /**
     * data for testing testChangingCoordinates method.
     * @return repository, string and list with coordinates
     */
    @DataProvider()
    public Object[][] dataProviderForChangingCoordinates() {
        return new Object[][]{ {repository, "3", newCoordinates}
        };
    }

    /**
     * tests changing coordinate in cube.
     * @param repository - repository
     * @param id - id
     * @param list - list
     */
    @Test(description = "test positive", priority = 1,
            dataProvider = "dataProviderForChangingCoordinates")
    public void testChangingCoordinates(CubeRepository repository, String id,
                                        List<Double> list) {
        repository.changeCoordinates(id, list);
        List<CubeData> cubesData = repository.getCubesData();
        double actual = 0;
        for (int i = 0; i < cubesData.size(); i++) {
            if (cubesData.get(i).getId().equals(id)) {
                actual = cubesData.get(i).getVolume();
            }
        }
        double expected = 125;
        Assert.assertEquals(actual, expected);
    }

}
