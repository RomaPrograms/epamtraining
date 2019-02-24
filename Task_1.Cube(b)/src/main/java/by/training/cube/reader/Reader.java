/*
 * These package contain the class {@code Reader}
 *
 * @since 1.0
 * @author Roman
 * @version 1.0
 */
package by.training.cube.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * Reader is class which gets information from our file.
 * @author Roman
 * @version 1.0
 */
public class Reader {
    /**
     * string for problems with file.
     */
    private static final String PROBLEMS_WITH_FILE =
            "File has problems, choose another file please.";
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
    public ArrayList<String> readFromFile(final String file) {
        String str;
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(new File(file));
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while (bufferedReader.ready()) {
                str = bufferedReader.readLine();
                if (!str.isEmpty()) {
                    arrayList.add(str);
                }
            }
        } catch (FileNotFoundException ex) {
            new Exception(FILE_NOT_FOUND);
        } catch (IOException ex) {
            new Exception(INCORRECT_DATA);
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                new Exception(PROBLEMS_WITH_FILE);
            }

        }
        return arrayList;
    }
}
