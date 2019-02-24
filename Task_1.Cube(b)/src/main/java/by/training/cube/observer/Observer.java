package by.training.cube.observer;

import by.training.cube.action.CubeEvent;

/**
 * interface observer.
 */
public interface Observer {
    /**
     * function react to some events.
     * @param event - event
     * @param id - id
     */
    void handleEvent(CubeEvent event, String id);
}
