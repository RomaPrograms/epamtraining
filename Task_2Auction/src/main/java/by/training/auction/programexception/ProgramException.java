package by.training.auction.programexception;

/**ProgramException is class with Exceptions.
 * @author Roman
 * @version 1.0
 * */
public class ProgramException extends Exception {

    /**
     * Constructor - creates exception.
     */
    public ProgramException() {
    }

    /**
     * Constructor with message and exception.
     *
     * @param str - message
     * @param exception - exception
     */
    public ProgramException(final String str, final Throwable exception) {
        super(str, exception);
    }

    /**
     * Constructor with exception.
     * @param exception - exception
     */
    public ProgramException(final Throwable exception) {
        super(exception);
    }

    /**
     * Constructor - initialized exception with string and transmit message.
     * @param str - message for Exception
     */
    public ProgramException(final String str) {
        super(str);
    }
}
