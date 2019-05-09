package by.training.lakes_paradise.exception;

/**
 * Class exception.
 */
public class PersistentException extends Exception {
    /**
     * Zero-argument constructor.
     */
    public PersistentException() {
    }

    /**
     * Two-argument constructor.
     *
     * @param message - message which we want to use after throwing an exception
     * @param cause   - exception that called this exception
     */
    public PersistentException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * One-argument constructor.
     *
     * @param message - message which we want to use after throwing an exception
     */
    public PersistentException(final String message) {
        super(message);
    }

    /**
     * No-argument constructor.
     *
     * @param cause - exception that called this exception
     */
    public PersistentException(final Throwable cause) {
        super(cause);
    }
}