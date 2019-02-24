package by.training.cube.action;

import by.training.cube.entity.Cube;
import java.util.EventObject;

/**
 * class registrates changes in cubes.
 */
public class CubeEvent extends EventObject {

    /**
     * Constructor - pinpoint which kind of cube was changed.
     * @param source - cube
     */
    public CubeEvent(final Cube source) {
        super(source);
    }

    /**
     * function returns source of cube after event.
     * @return cube with changes
     */
    @Override
    public Cube getSource() {
        return (Cube) super.getSource();
    }
}
