package by.training.cube.finding;

import by.training.cube.entity.Cube;
import by.training.cube.entity.CubeData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * class for finding cubes by id.
 */
public class FindById {
    /**
     * function finds all cubes with special id.
     * @param cubes - cubes
     * @param cubesData - data about cubes
     * @param information - id
     * @return arrayList of cubes with special id
     */
    public List<Cube> findById(final Map<String, Cube> cubes,
                                    final List<CubeData> cubesData,
                                    final String information) {

        List<Cube> arrayList = new ArrayList<>();

        for (int i = 0; i < cubesData.size();  i++) {
            if (cubesData.get(i).getId().equals(information)) {
                arrayList.add(cubes.get(cubesData.get(i).getId()));
            }
        }
        return arrayList;
    }
}
