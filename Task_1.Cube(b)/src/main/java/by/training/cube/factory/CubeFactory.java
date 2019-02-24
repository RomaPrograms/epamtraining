package by.training.cube.factory;

import by.training.cube.action.CubeAction;
import by.training.cube.entity.Cube;
import by.training.cube.entity.CubeData;

/**
 * class creates cube data when creates cube.
 */
public class CubeFactory implements Factory {

    /**
     *object of cubeAction class.
     */
    private CubeAction cubeAction = new CubeAction();

    /**
     * function creates data about cube.
     * @param id - id
     * @param name - name
     * @param cube - cube
     * @return object of cubeData class with information about cube
     */
    public CubeData create(final String id, final String name,
                           final Cube cube) {
        double sideOfCube = cubeAction.calculateSide(cube.getA(), cube.getB());

        CubeData cubeData = new CubeData();
        cubeData.setId(id);
        cubeData.setName(name);
        cubeData.setVolume(cubeAction.calculateVolume(sideOfCube));
        cubeData.setSquare(cubeAction.calculateSquare(sideOfCube));
        return cubeData;
    }
}
