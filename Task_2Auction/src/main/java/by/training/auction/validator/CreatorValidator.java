package by.training.auction.validator;

import by.training.auction.programexception.ProgramException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Map;

/**
 * validator which checks information in creator.
 */
public class CreatorValidator {
    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER =
            LogManager.getLogger(CreatorValidator.class);
    /**
     * statement for incorrect line.
     */
    private static final String INCORRECT_LINE = "Incorrect line!";

    /**isRightParameters pinpoints is our parameters correct for initialization.
     * @param list - string with parameters from file
     * @return true if we got valid string, otherwise false
     */
    public boolean isRightParameters(final Map.Entry<String, Integer> list) {
        try {
            if (list.getValue() <= 0) {
               throw new ProgramException();
            }
        } catch (ProgramException ex) {
            LOGGER.error(INCORRECT_LINE);
            return false;
        }
        return true;
    }
}
