package by.training.lakes_paradise.action;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class for executing method of required action class for processing
 * user request.
 */
public class ActionManagerRealization implements ActionManager {

    /**
     * Factory for creating necessary DAO classes.
     */
    private ServiceFactory factory;

    /**
     * One-argument constructor.
     *
     * @param newFactory - value of factory property.
     */
    ActionManagerRealization(final ServiceFactory newFactory) {
        this.factory = newFactory;
    }

    /**
     * Method which calls method of action classes for executing
     * current action.
     *
     * @param action - name of current action
     * @param request - current request
     * @param response - current response
     * @return URL of jsp page which should be shown or name of action which
     * should be executed next
     * @throws PersistentException - exception connected with DAO
     */
    @Override
    public Forward execute(
            final Action action, final HttpServletRequest request,
            final HttpServletResponse response) throws PersistentException {
        action.setFactory(factory);
        return action.exec(request, response);
    }

    /**
     * Method which closes resources of factory property.
     */
    @Override
    public void close() {
        factory.close();
    }
}
