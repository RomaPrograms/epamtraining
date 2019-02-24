package by.training.cube.entity;

import by.training.cube.action.CubeAction;

/**CubeData is class, with all cube parameters.
 * @author Roman
 * @version 1.0
 */
public class CubeData {
    /**
     *object of cubeAction class.
     */
    private final CubeAction cubeAction = new CubeAction();

    /**
     * id of cube.
     */
    private String id;
    /**
     * name of cube.
     */
    private String name;
    /**
     * volume of cube.
     */
    private double volume;
    /**
     * square of cube.
     */
    private double square;

    /**
     * function of getting id of cube.
     * @return cube id
     */
    public String getId() {
        return id;
    }
    /**
     * function of setting id of cube.
     * @param cubeId - cube id
     */
    public void setId(final String cubeId) {
        this.id = cubeId;
    }

    /**
     * function of getting name of cube.
     * @return cube name
     */
    public String getName() {
        return name;
    }
    /**
     * function of setting name of cube.
     * @param cubeName - cube name
     */
    public void setName(final String cubeName) {
        this.name = cubeName;
    }

    /**
     * function of getting volume of cube.
     * @return cube volume
     */
    public double getVolume() {
        return volume;
    }
    /**
     * function of setting volume of cube.
     * @param cubeVolume - cube volume
     */
    public void setVolume(final double cubeVolume) {
        this.volume = cubeVolume;
    }

    /**
     * function of getting square of cube.
     * @return cube square
     */
    public double getSquare() {
        return square;
    }
    /**
     * function of setting square of cube.
     * @param cubeSquare - cube square
     */
    public void setSquare(final double cubeSquare) {
        this.square = cubeSquare;
    }

    /**
     * function shows information about cube in line.
     * @return cube data
     */
    @Override
    public String toString() {
        return "CubeData{" + "cubeAction=" + cubeAction + ", id='" + id
                + ", name='" + name + ", volume=" + volume
                + ", square=" + square + '}';
    }
}
