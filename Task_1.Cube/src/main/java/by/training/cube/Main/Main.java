/**
 * These package contain the class {@code Main}
 *
 * @since 1.0
 * @author Roman
 * @version 1.0
 */
package by.training.cube.Main;

import by.training.cube.creator.Creator;
import by.training.cube.entity.Cube;
import by.training.cube.parser.Parser;
import by.training.cube.reader.Reader;

import java.util.ArrayList;

/**Main is class were we start our program.
 * @author Roman
 * @version 1.0
 */

public class Main {
    /**
     * main starts our program.
     * @param args - arguments which we can transmit from console
     */
    public static void main(final String[] args) {
        Reader reader = new Reader();
        Parser parser = new Parser();
        Creator creator = new Creator();
        ArrayList<String> arrayListStrings =
                reader.readFromFile("src\\main\\resources\\data1.txt");
        ArrayList<ArrayList<Double>> arrayListsDoubles =
                parser.parse(arrayListStrings);
        ArrayList<Cube> cubes = creator.create(arrayListsDoubles);

        for (int i = 0; i < cubes.size(); i++) {
            System.out.println(cubes.get(i));
        }
    }
}
