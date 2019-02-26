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
     * Constructor with message and exception.
     * @param str - message
     * @param exception - exception
     */
    public DataException(final String str, final Throwable exception) {
        super(str, exception);
    }

    /**
     * Constructor with exception.
     * @param exception - exception
     */
    public DataException(final Throwable exception) {
        super(exception);
    }
    /**
     * Constructor - initialized exception with string and transmit message.
     * @param str - message for Exception
     */
    public DataException(final String str) {
        super(str);
    }
}
