package by.training.cube.entity;

public class Cube {
    /**Cube is class, with all cube parameters*/

    private Point a;
    private Point b;
    private Point c;

    public Cube(Point a, Point b, Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Point getA() {
        return a;
    }

    public void setA(Point a) {
        this.a = a;
    }

    public Point getB() {
        return b;
    }

    public void setB(Point b) {
        this.b = b;
    }

    public Point getC() {
        return c;
    }

    public void setC(Point c) {
        this.c = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this.hashCode() != o.hashCode()){
            return false;
        }
        if (!(o instanceof Cube)){
            return false;
        }

        return ((Cube)o).getA().equals(getA())==true &&
                ((Cube)o).getB().equals(getB())==true &&
                ((Cube)o).getC().equals(getC())==true;
    }

    @Override
    public int hashCode() {
        return getA().hashCode()* getB().hashCode()* getC().hashCode();
    }

    @Override
    public String toString() {
        return "a = {" + a.toString() + "}, b = {" + b.toString() + "}, c = {" + c.toString() + "}";
    }
}
