/**
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
     * @param xCoord - coordinate X
     * @param yCoord - coordinate Y
     * @param zCoord - coordinate Z
     */
    public Point(final double xCoord, final double yCoord,
                 final double zCoord) {
        this.x = xCoord;
        this.y = yCoord;
        this.z = zCoord;
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
     * @param xCoord - X coordinate
     */
    public void setX(final double xCoord) {
        this.x = xCoord;
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
     * @param yCoord - Y coordinate
     */
    public void setY(final double yCoord) {
        this.y = yCoord;
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
     * @param zCoord - Z coordinate
     */
    public void setZ(final double zCoord) {
        this.z = zCoord;
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
        if (this.hashCode() != object.hashCode()) {
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
