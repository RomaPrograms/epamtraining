package by.training.cube.validator;

import by.training.cube.exceptions.DataException;

import java.util.ArrayList;

/**
 * validator for class parser.
 */
public class ParserValidator {

    /**
     * statement for incorrect line.
     */
    private static final String INCORRECT_LINE = "Incorrect line!";

    /**
     * function checks string for right parameters.
     * @param string - string
     * @return true if string is correct
     */
    public boolean validate(final String string) {
        String[] stringMassive = string.split("\\s");
        try {
            ArrayList<Double> arrayListDouble = new ArrayList<>();
            for (int i = 0; i < stringMassive.length; i++) {
                arrayListDouble.add(Double.parseDouble(stringMassive[i]));
            }
        } catch (NumberFormatException ex) {
            new DataException(INCORRECT_LINE);
            return false;
        }
        return true;
    }
}
