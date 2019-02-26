/*
 * These package contain the class {@code Reader}
 *
 * @since 1.0
 * @author Roman
 * @version 1.0
 */
package by.training.cube.reader;

import by.training.cube.action.CubeAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reader is class which gets information from our file.
 * @author Roman
 * @version 1.0
 */
public class Reader {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER = LogManager.getLogger(CubeAction.class);
    /**
     * string for incorrect data.
     */
    private static final String INCORRECT_DATA =
            "File has incorrect data, please refactor your file";
    /**
     * string for problems with existence of file.
     */
    private static final String FILE_NOT_FOUND =
            "File wasn't found. Please, check name of the file.";
    /**
     * arrayList with strings from file with data.
     */
    private ArrayList<String> arrayList = new ArrayList<>();
    /**readFromFile reads data from file.
     * @param file - file with data
     * @return returns arrayList of strings with data from file*/
    public List<String> readFromFile(final String file) {
        String str;
        try (FileReader fileReader = new FileReader(new File(file))) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()) {
                str = bufferedReader.readLine();
                if (!str.isEmpty()) {
                    arrayList.add(str);
                }
            }
            bufferedReader.close();

        } catch (FileNotFoundException ex) {
            LOGGER.debug(FILE_NOT_FOUND);
        } catch (IOException ex) {
            LOGGER.debug(INCORRECT_DATA);
        }
        return arrayList;
    }
}
