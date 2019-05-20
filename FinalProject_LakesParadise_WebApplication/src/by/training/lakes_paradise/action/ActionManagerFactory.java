package by.training.lakes_paradise.action;

import by.training.lakes_paradise.service.ServiceFactory;

/**
 * Class with static method for getting instance of
 * {@code ActionManagerRealization} class.
 */
public final class ActionManagerFactory {

    /**
     * Private constructor for utility class.
     */
    private ActionManagerFactory() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Method returns initialized instance of {@code ActionManagerRealization}
     * class.
     *
     * @param factory - factory for creating instances of service classes
     * @return instance of {@code ActionManagerRealization} class
     */
    public static ActionManager getManager(final ServiceFactory factory) {
        return new ActionManagerRealization(factory);
    }
}
