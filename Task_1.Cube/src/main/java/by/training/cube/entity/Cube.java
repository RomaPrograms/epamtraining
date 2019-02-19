/*
 * These package contain the class {@code Cube}.
 *
 * @since 1.0
 * @author Roman
 * @version 1.0
 */
package by.training.cube.entity;

/**
 * Cube is class, with all cube parameters.
 * @author Roman
 * @version 1.0
 */
public class Cube {

    /**
     * first point of cube.
     */
    private Point a;
    /**
     * second point of cube.
     */
    private Point b;
    /**
     * third point of cube.
     */
    private Point c;
    /**
     * Constructor - creates new object from 3 points.
     * @param aPoint - first point
     * @param bPoint - second point
     * @param cPoint - third point
     * */
    public Cube(final Point aPoint, final Point bPoint, final Point cPoint) {
        this.a = aPoint;
        this.b = bPoint;
        this.c = cPoint;
    }

    /**
     * function of getting coordinate of first point.
     * @return point A with coordinates
     */
    public Point getA() {
        return a;
    }

    /**
     * function of setting coordinate of first point.
     * @param aPoint - point A with coordinates
     */
    public void setA(final Point aPoint) {
        this.a = aPoint;
    }

    /**
     * function of getting coordinate of second point.
     * @return point B with coordinates
     */
    public Point getB() {
        return b;
    }

    /**
     * function of setting coordinate of first point.
     * @param bPoint - point B with coordinates
     */
    public void setB(final Point bPoint) {
        this.b = bPoint;
    }

    /**
     * function of getting coordinate of third point.
     * @return point C with coordinates
     */
    public Point getC() {
        return c;
    }

    /**
     * function of setting coordinate of first point.
     * @param cPoint - point C with coordinates
     */
    public void setC(final Point cPoint) {
        this.c = cPoint;
    }

    /**
     * Compares this cube to the specified object.  The result is {@code
     * true} if and only if the argument is not {@code null} and
     * object that represents the same sequence of characters as this
     * object.
     *
     * @param object
     *         The object to compare this {@code Cube} against
     *
     * @return  {@code true} if the given object represents a {@code Cube}
     *          equivalent to this cube, {@code false} otherwise
     */
    @Override
    public boolean equals(final Object object) {
        if (this.hashCode() != object.hashCode()) {
            return false;
        }
        if (!(object instanceof Cube)) {
            return false;
        }

        return (((Cube) object).getA().equals(getA()))
                && (((Cube) object).getB().equals(getB()))
                && (((Cube) object).getC().equals(getC()));
    }

    /**
     * Creates special hashcode for every object of class Point.
     * @return hashcode of requested object
     */
    @Override
    public int hashCode() {
        return getA().hashCode() * getB().hashCode() * getC().hashCode();
    }

    /**
     * Shows information about requested object.
     * @return returns string with data about object
     */
    @Override
    public String toString() {
        return "a = {" + a.toString() + "}, b = {" + b.toString() + "}, c = {"
                + c.toString() + "}";
    }
}
