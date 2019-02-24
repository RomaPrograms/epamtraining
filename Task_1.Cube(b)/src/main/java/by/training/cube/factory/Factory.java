package by.training.cube.factory;

import by.training.cube.entity.Cube;
import by.training.cube.entity.CubeData;

/**
 * class creates cube data when creates cube.
 */
public interface Factory {
    /**
     * function creates data about cube.
     * @param id - id
     * @param name - name
     * @param cube - cube
     * @return object of cubeData class with information about cube
     */
    CubeData create(String id, String name, Cube cube);
}
