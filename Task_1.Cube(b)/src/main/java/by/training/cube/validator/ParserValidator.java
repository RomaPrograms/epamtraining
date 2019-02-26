package by.training.cube.validator;

import by.training.cube.action.CubeAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * validator for class parser.
 */
public class ParserValidator {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER = LogManager.getLogger(CubeAction.class);
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
            List<Double> arrayListDouble = new ArrayList<>();
            for (int i = 0; i < stringMassive.length; i++) {
                arrayListDouble.add(Double.parseDouble(stringMassive[i]));
            }
        } catch (NumberFormatException ex) {
            LOGGER.debug(INCORRECT_LINE);
            return false;
        }
        return true;
    }
}
