package by.training.lakes_paradise.action;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ActionManagerRealization implements ActionManager {

    private ServiceFactory factory;

    public ActionManagerRealization(final ServiceFactory factory) {
        this.factory = factory;
    }

    @Override
    public Forward execute(
            final Action action, final HttpServletRequest request,
            final HttpServletResponse response) throws PersistentException {
        action.setFactory(factory);
        return action.exec(request, response);
    }

    @Override
    public void close() {
        factory.close();
    }
}
