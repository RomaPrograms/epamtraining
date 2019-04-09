package by.training.webparsing.exception;

/**ProgramException is class with Exceptions.
 * @author Roman
 * @version 1.0
 */
public class ParsingException extends Exception {
    /**
     * Constructor - creates exception.
     */
    public ParsingException() {
    }

    /**
     * Constructor with message and exception.
     *
     * @param str - message
     * @param exception - exception
     */
    public ParsingException(final String str, final Throwable exception) {
        super(str, exception);
    }

    /**
     * Constructor with exception.
     * @param exception - exception
     */
    public ParsingException(final Throwable exception) {
        super(exception);
    }

    /**
     * Constructor - initialized exception with string and transmit message.
     * @param str - message for Exception
     */
    public ParsingException(final String str) {
        super(str);
    }
}
