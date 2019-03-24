package by.training.informhandling.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
/**
 * class that reads information from file.
 */
public class Reader {
    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER = LogManager.getLogger(Reader.class);
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
     * readFromFile reads data from file.
     * @param file - file with data
     * @return returns arrayList of strings with data from file
     */
    public String readFromFile(final String file) {
        StringBuilder string = new StringBuilder();
        String line;
        try (FileReader fileReader = new FileReader(new File(file))) {
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()) {
                line = bufferedReader.readLine();
                if (!line.isEmpty()) {
                    string.append(line);
                }
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            LOGGER.error(FILE_NOT_FOUND);
        } catch (IOException ex) {
            LOGGER.error(INCORRECT_DATA);
        }
        return string.toString();
    }
}
