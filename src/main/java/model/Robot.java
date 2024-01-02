package model;

public class Robot {
    private double x;
    private double y;
    private double speed;
    private boolean moving;
    private String condition;

    public Robot(double x, double y, double speed){
        this.x=x;
        this.y=y;
        this.speed=speed;
        this.moving=false;
        this.condition="";
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
