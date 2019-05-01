package by.training.lakes_paradise.exception;

public class IncorrectDataException extends Exception {
    /**
     * No-argument constructor.
     */
    public IncorrectDataException() {
    }

    /**
     * Two-argument constructor.
     *
     * @param message - message which we want to use after throwing an exception
     * @param cause   - exception that called this exception
     */
    public IncorrectDataException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public IncorrectDataException(final String parameter, final String value) {
        super("Parameter '" + parameter + "' got empty or incorrect value '"
                + "'");
    }

    /**
     * One-argument constructor.
     *
     * @param message - message which we want to use after throwing an exception
     */
    public IncorrectDataException(final String message) {
        super(message);
    }

    /**
     * No-argument constructor.
     *
     * @param cause - exception that called this exception
     */
    public IncorrectDataException(final Throwable cause) {
        super(cause);
    }
}
