package by.training.lakes_paradise.action.entity;

import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

abstract public class Action {
    private String name;

    protected ServiceFactory factory;

    public String getName() {
        return name;
    }

    public void setName(final String actionName) {
        this.name = actionName;
    }

    public void setFactory(final ServiceFactory serviceFactory) {
        this.factory = serviceFactory;
    }

    abstract public Forward exec(HttpServletRequest request,
                                 HttpServletResponse response) throws PersistentException;
}
