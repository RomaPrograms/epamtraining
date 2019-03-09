package by.training.auction.validator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.StringTokenizer;

/**
 * validator for class parser.
 */
public class ParserValidator {

    /**
     * Logger for creation notes to some appender.
     */
    private static final Logger LOGGER = LogManager.getLogger(
            ParserValidator.class);
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
        try {
            StringTokenizer stringTokenizer = new StringTokenizer(
                    string, " ");
            String.valueOf(stringTokenizer.nextToken());
            Integer.parseInt(stringTokenizer.nextToken());
        } catch (NumberFormatException ex) {
            LOGGER.debug(INCORRECT_LINE);
            return false;
        }
        return true;
    }
}
