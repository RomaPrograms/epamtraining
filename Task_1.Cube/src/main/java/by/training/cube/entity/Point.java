package by.training.cube.entity;

public class Point {
    /**Point is class, with all point parameters*/

    private double x;
    private double y;
    private double z;

    Point(){}

    public Point(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this.hashCode() != o.hashCode()){
            return false;
        }
        if (!(o instanceof Point)){
            return false;
        }

        return Double.compare(((Point)o).getX(), getX()) == 0 &&
                Double.compare(((Point)o).getY(), getY()) == 0 &&
                Double.compare(((Point)o).getZ(), getZ()) == 0;
    }

    @Override
    public int hashCode() {
        return (int)(getX()*getY()*getZ());
    }

    @Override
    public String toString() {
        return "x = " + x + ", y = " + y + ", z = " + z;
    }
}
