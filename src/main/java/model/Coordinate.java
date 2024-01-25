package model;

public class Coordinate implements CoordinateInterface {
    private double x;
    private double y;

    public Coordinate(double x, double y){
        this.x=x;
        this.y=y;
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

    public Coordinate setNewCoordinate(CoordinateInterface coordinate){
        return new Coordinate(this.x+getX(),this.y+getY());
    }
}
