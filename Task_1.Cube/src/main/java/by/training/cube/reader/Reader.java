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
     * arrayList with strings from file with data.
     */
    private ArrayList<String> arrayList = new ArrayList<String>();
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
                if (str.equals("")) {
                } else {
                    arrayList.add(str);
                }
            }
        } catch (FileNotFoundException ex) {
            new Exception("File wasn't found. Please, check name of the file.");
        } catch (IOException ex) {
            new Exception("File has incorrect data, please refactor your file");
        }
        finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                new Exception("File can't be closed, you have problems with "
                        + "your file.");
            }

        }
        return arrayList;
    }
}
