package by.training.lakes_paradise.action;

import by.training.lakes_paradise.service.ServiceFactory;

public class ActionManagerFactory {
    public static ActionManager getManager(final ServiceFactory factory) {
        return new ActionManagerRealization(factory);
    }
}
