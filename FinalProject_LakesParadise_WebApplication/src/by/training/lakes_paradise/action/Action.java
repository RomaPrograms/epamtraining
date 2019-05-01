package by.training.lakes_paradise.action;

import by.training.lakes_paradise.exception.PersistentException;
import by.training.lakes_paradise.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

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

    abstract public Action.Forward exec( HttpServletRequest request,
            HttpServletResponse response) throws PersistentException;

    public static class Forward {
        private String forward;
        private boolean redirect;
        private Map<String, Object> attributes = new HashMap<>();

        public Forward(String forward, boolean redirect) {
            this.forward = forward;
            this.redirect = redirect;
        }

        public Forward(final String forward) {
            this(forward, true);
        }

        public String getForward() {
            return forward;
        }

        public void setForward(final String forward) {
            this.forward = forward;
        }

        public boolean isRedirect() {
            return redirect;
        }

        public void setRedirect(final boolean redirect) {
            this.redirect = redirect;
        }

        public Map<String, Object> getAttributes() {
            return attributes;
        }
    }
}
