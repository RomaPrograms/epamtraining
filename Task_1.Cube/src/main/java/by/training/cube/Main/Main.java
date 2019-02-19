/*
 * These package contain the class {@code Main}
 *
 * @since 1.0
 * @author Roman
 * @version 1.0
 */
package by.training.cube.Main;

import by.training.cube.action.CubeAction;
import by.training.cube.creator.Creator;
import by.training.cube.entity.Cube;
import by.training.cube.parser.Parser;
import by.training.cube.reader.Reader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**Main is class were we start our program.
 * @author Roman
 * @version 1.0
 */

public class Main {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER = LogManager.getLogger(CubeAction.class);

    /**
     * main starts our program.
     * @param args - arguments which we can transmit from console
     */
    public static void main(final String[] args) {
        Reader reader = new Reader();
        LOGGER.info("Object of class Reader was created.");
        Parser parser = new Parser();
        LOGGER.info("Object of class Parser was created.");
        Creator creator = new Creator();
        LOGGER.info("Object of class Creator was created.");
        ArrayList<String> arrayListStrings =
                reader.readFromFile("src\\main\\resources\\data1.txt");
        LOGGER.info("Information from file were read.");
        ArrayList<ArrayList<Double>> arrayListsDoubles =
                parser.parse(arrayListStrings);
        LOGGER.info("Information from file were parsed.");
        ArrayList<Cube> cubes = creator.create(arrayListsDoubles);
        LOGGER.info("ArrayList of cubes wes created.");

        for (int i = 0; i < cubes.size(); i++) {
            System.out.println(cubes.get(i));
        }
    }
}
