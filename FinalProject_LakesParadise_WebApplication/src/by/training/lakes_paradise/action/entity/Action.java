package by.training.lakes_paradise.action.entity;

import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract public class Action {
    /**
     * Action name.
     */
    private String name;

    /**
     * Factory for creating services.
     */
    protected ServiceFactory factory;

    /**
     * Gets the value of name property.
     *
     * @return value of name property
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of name property.
     *
     * @param actionName - value of name property
     */
    public void setName(final String actionName) {
        this.name = actionName;
    }

    /**
     * Sets the value of factory property.
     *
     * @param serviceFactory - value of factory property
     */
    public void setFactory(final ServiceFactory serviceFactory) {
        this.factory = serviceFactory;
    }

    /**
     * Common method for calling handler of current action.
     *
     * @param request  - user request
     * @param response - user response
     * @return URL of jsp page which should be shown
     * @throws PersistentException - exception connected with DAO
     */
    abstract public Forward exec(HttpServletRequest request,
                                 HttpServletResponse response)
            throws PersistentException;
}
