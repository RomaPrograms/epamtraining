/*
 * These package contain the class {@code Point}
 *
 * @since 1.0
 * @author Roman
 * @version 1.0
 */
package by.training.cube.entity;

/**Point is class, with all point parameters.
 * @author Roman
 * @version 1.0
 */

public class Point {

    /**
     * X coordinate.
     */
    private double x;
    /**
     * Y coordinate.
     */
    private double y;

    /**
     * Z coordinate.
     */
    private double z;

    /**
     * Constructor - creates new object from 3 points.
     * @param xCoordinate - coordinate X
     * @param yCoordinate - coordinate Y
     * @param zCoordinate - coordinate Z
     */
    public Point(final double xCoordinate, final double yCoordinate,
                 final double zCoordinate) {
        this.x = xCoordinate;
        this.y = yCoordinate;
        this.z = zCoordinate;
    }

    /**
     * function of getting X coordinate.
     * @return X coordinate
     */
    public double getX() {
        return x;
    }

    /**
     * function of setting X coordinate.
     * @param xCoordinate - X coordinate
     */
    public void setX(final double xCoordinate) {
        this.x = xCoordinate;
    }

    /**
     * function of getting Y coordinate.
     * @return Y coordinate
     */
    public double getY() {
        return y;
    }

    /**
     * function of setting Y coordinate.
     * @param yCoordinate - Y coordinate
     */
    public void setY(final double yCoordinate) {
        this.y = yCoordinate;
    }

    /**
     * function of getting Z coordinate.
     * @return Z coordinate
     */
    public double getZ() {
        return z;
    }

    /**
     * function of setting Z coordinate.
     * @param zCoordinate - Z coordinate
     */
    public void setZ(final double zCoordinate) {
        this.z = zCoordinate;
    }

    /**
     * Compares this cube to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null} and
     * object that represents the same sequence of characters as this
     * object.
     *
     * @param object
     *         The object to compare this {@code Point} against
     *
     * @return  {@code true} if the given object represents a {@code Point}
     *          equivalent to this cube, {@code false} otherwise
     */
    @Override
    public boolean equals(final Object object) {
        if (object != null && this.hashCode() != object.hashCode()) {
            return false;
        }

        if (!(object instanceof Point)) {
            return false;
        }

        return Double.compare(((Point) object).getX(), getX()) == 0
                && Double.compare(((Point) object).getY(), getY()) == 0
                && Double.compare(((Point) object).getZ(), getZ()) == 0;
    }

    /**
     * Creates special hashcode for every object of class Point.
     * @return hashcode of requested object
     */
    @Override
    public int hashCode() {
        return (int) (getX() * getY() * getZ());
    }

    /**
     * Shows information about requested object.
     * @return returns string with data about object
     */
    @Override
    public String toString() {
        return "x = " + x + ", y = " + y + ", z = " + z;
    }
}
