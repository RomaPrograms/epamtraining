/*
 * These package contain the class {@code DataException}.
 *
 * @since 1.0
 * @author Roman
 * @version 1.0
 */
package by.training.cube.exceptions;

/**DataException is class with Exceptions.
 * @author Roman
 * @version 1.0
 * */
public class DataException extends Exception {

    /**
     * Constructor - creates exception.
     */
    public DataException() { }

    /**
     * Constructor - initialized exception with string and transmit message.
     * @param str - message for Exception
     */
    public DataException(final String str) {
        super(str);
    }
}
