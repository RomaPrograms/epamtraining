package by.training.cube.creator;

import by.training.cube.entity.Cube;
import by.training.cube.entity.Point;

import java.util.ArrayList;

public class Creator {
    /**
     * Creator is class where we creates our cube if file have correct data
     */
    ArrayList<Cube> cubes = new ArrayList<Cube>();

    public ArrayList<Cube> create(ArrayList<ArrayList<Double>> arrayList) {
        /**Method create creates objects of class cube*/

        for (int i = 0; i < arrayList.size(); i++) {
            cubes.add(new Cube(new Point(arrayList.get(i).get(0), arrayList.get(i).get(1), arrayList.get(i).get(2)),
                    new Point(arrayList.get(i).get(3), arrayList.get(i).get(4), arrayList.get(i).get(5)),
                    new Point(arrayList.get(i).get(6), arrayList.get(i).get(7), arrayList.get(i).get(8))));
        }

        return cubes;
    }

}
