package by.training.cube.finding;

import by.training.cube.entity.Cube;
import by.training.cube.entity.CubeData;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * class for finding cubes by name.
 */
public class FindByName {
    /**
     * function finds all cubes with special name.
     * @param cubes - cubes
     * @param cubesData - data about cubes
     * @param information - name
     * @return arrayList of cubes with special name
     */
    public List<Cube> findByName(final Map<String, Cube> cubes,
                                 final List<CubeData> cubesData,
                                 final String information) {

        List<Cube> arrayList = new ArrayList<>();

        for (int i = 0; i < cubesData.size(); i++) {
            if (cubesData.get(i).getName().equals(information)) {
                arrayList.add(cubes.get(cubesData.get(i).getId()));
            }
        }
        return arrayList;
    }
}
