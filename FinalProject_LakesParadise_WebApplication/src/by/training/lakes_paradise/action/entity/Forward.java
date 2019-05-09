package by.training.lakes_paradise.action.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Class which helps isRedirect requires, save attributes for the request and
 * save URL of next jsp page.
 */
public class Forward {
    /**
     * URL of next jsp page or name of next action.
     */
    private String forward;
    /**
     * Shows is request should be redirected.
     */
    private boolean isRedirect;
    /**
     * Attributes which should be saved after redirection.
     */
    private Map<String, Object> attributes = new HashMap<>();

    /**
     * Two-argument constructor.
     *
     * @param newForward - value of forward property
     * @param newIsRedirect - value of isRedirect property
     */
    public Forward(String newForward, boolean newIsRedirect) {
        this.forward = newForward;
        this.isRedirect = newIsRedirect;
    }

    /**
     * One-argument constructor, which calls two-argument constructor where
     * second argument will be true.
     *
     * @param forward
     */
    public Forward(final String forward) {
        this(forward, true);
    }

    /**
     * Gets the value of forward property.
     *
     * @return value of forward property
     */
    public String getForward() {
        return forward;
    }

    /**
     * Sets the value of forward property.
     *
     * @param newForward - value of forward property
     */
    public void setForward(final String newForward) {
        this.forward = newForward;
    }

    /**
     * Gets the value of isRedirect property.
     *
     * @return value of isRedirect property
     */
    public boolean isRedirect() {
        return isRedirect;
    }

    /**
     * Sets the value of isRedirect property.
     *
     * @param newIsRedirect - value of isRedirect property
     */
    public void setRedirect(final boolean newIsRedirect) {
        this.isRedirect = newIsRedirect;
    }

    /**
     * Gets the value of attributes property.
     *
     * @return value of attributes property
     */
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    /**
     * Sets the value of attributes property.
     *
     * @param newAttributes - value of attributes property
     */
    public void setAttributes(final Map<String, Object> newAttributes) {
        this.attributes = newAttributes;
    }
}
