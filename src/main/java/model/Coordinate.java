package model;

public class Coordinate implements CoordinateInterface {
    private final double x;
    private final double y;

    public Coordinate(double x, double y){
        this.x=x;
        this.y=y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Coordinate setNewCoordinate(CoordinateInterface coordinate){
        return new Coordinate(this.x+getX(),this.y+getY());
    }
}
