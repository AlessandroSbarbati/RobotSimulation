package model;

public class CircularArea implements Area{
    private double centerX;
    private double centerY;
    private double radius;

    public CircularArea(double centerX, double centerY, double radius){
        this.centerX=centerX;
        this.centerY=centerY;
        this.radius=radius;
    }
    @Override
    public boolean contains(double x, double y) {
        double distanceSquared = Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2);
        return distanceSquared <= Math.pow(radius, 2);
    }
}
