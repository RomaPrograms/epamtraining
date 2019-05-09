package by.training.lakes_paradise.exception;

/**
 * Class which declares exception connected with incorrect validation of
 * some parameters
 */
public class IncorrectDataException extends Exception {
    /**
     * Zero-argument constructor.
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

    /**
     * Two-argument constructor.
     *
     * @param parameter - name of incorrect parameter
     * @param value - value of incorrect parameter
     */
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
