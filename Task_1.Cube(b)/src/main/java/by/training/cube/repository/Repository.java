package by.training.cube.repository;

import by.training.cube.entity.Cube;
import java.util.List;

/**
 * repository interface with methods.
 */
public interface Repository {
    /**
     * function adds elements.
     * @param cube - cube
     * @param id - id of cube
     * @param name - name of cube
     */
    void add(Cube cube, String id, String name);

    /**
     * function deletes cube by id.
     * @param id - id of cube
     */
    void delete(String id);

    /**
     * function changes coordinate of cube.
     * @param id - cube id
     * @param coordinates - cube coordinate
     */
    void changeCoordinates(String id, List<Double> coordinates);
}
