package by.training.cube.finding;

import by.training.cube.entity.Cube;
import by.training.cube.entity.CubeData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * class for finding cubes by square.
 */
public class FindBySquare {
    /**
     * function finds all cubes with special square.
     * @param cubes - cubes
     * @param cubesData - data about cubes
     * @param information - square
     * @return arrayList of cubes with special square
     */
    public List<Cube> findBySquare(final Map<String, Cube> cubes,
                                   final List<CubeData> cubesData,
                                   final double information) {

        List<Cube> arrayList = new ArrayList<>();

        for (int i = 0; i < cubesData.size(); i++) {
            if (cubesData.get(i).getSquare() == information) {
                arrayList.add(cubes.get(cubesData.get(i).getId()));
            }
        }
        return arrayList;
    }
}
