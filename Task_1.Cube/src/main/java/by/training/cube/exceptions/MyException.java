/*
 * These package contain the class {@code MyException}.
 *
 * @since 1.0
 * @author Roman
 * @version 1.0
 */
package by.training.cube.exceptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**MyException is class with Exceptions.
 * @author Roman
 * @version 1.0
 * */
public class MyException extends Exception {
    /**
     * Constructor - initialized exception and transmit message.
     * @param str - message for Exception
     */
    public MyException(final String str) {
        super(str);
    }
}
