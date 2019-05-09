package by.training.lakes_paradise.action;

import by.training.lakes_paradise.action.entity.Action;
import by.training.lakes_paradise.action.entity.Forward;
import by.training.lakes_paradise.exception.PersistentException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface which pinpoint methods for {@code ActionManagerRealization} class.
 */
public interface ActionManager {
    /**
     * Method which calls another method of action classes for executing
     * current action.
     *
     * @param action - name of current action
     * @param request - current request
     * @param response - current response
     * @return URL of jsp page which should be shown or name of action which
     * should be executed next
     * @throws PersistentException - exception connected with DAO
     */
    Forward execute(
            Action action, HttpServletRequest request,
            HttpServletResponse response) throws PersistentException;

    /**
     * Method which closes some resources.
     */
    void close();
}
