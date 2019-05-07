package by.training.lakes_paradise.action.entity;

import java.util.HashMap;
import java.util.Map;

public class Forward {
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

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}
