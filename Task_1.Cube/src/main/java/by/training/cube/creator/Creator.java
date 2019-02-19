/*
 * These package contain the class {@code Creator}.
 *
 * @since 1.0
 * @author Roman
 * @version 1.0
 */
package by.training.cube.creator;

import by.training.cube.entity.Cube;
import by.training.cube.entity.Point;

import java.util.ArrayList;

/**
 * Creator is class where we creates our cube if file have correct data.
 * @author Roman
 * @version 1.0
 */
public class Creator {
    /**
     * arrayList of cubes.
     */
    private ArrayList<Cube> cubes = new ArrayList<Cube>();
    /**create creates objects of class cube.
     * @param arrayList - ArrayList with cubes
     * @return returns ArrayList of cubes
     */
    public ArrayList<Cube> create(final
                                  ArrayList<ArrayList<Double>> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            cubes.add(new Cube(new Point(arrayList.get(i).get(0),
                    arrayList.get(i).get(1), arrayList.get(i).get(2)),
                    new Point(arrayList.get(i).get(3), arrayList.get(i).get(4),
                            arrayList.get(i).get(5)),
                    new Point(arrayList.get(i).get(6), arrayList.get(i).get(7),
                            arrayList.get(i).get(8))));
        }

        return cubes;
    }

}
