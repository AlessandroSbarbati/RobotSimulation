package model;

public class CoordinateRobot implements CoordinateInterface{
    private double x;
    private double y;
    private double velocita;
    private final double direzione;

    public CoordinateRobot(double x, double y, double velocita){
        this.direzione=Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
        this.x=(x/direzione)*velocita;
        this.y=(y/direzione)*velocita;
        this.velocita=velocita;
    }
    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public void setX(double x) {
        this.x=x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public void setY(double y) {
        this.y=y;
    }


    public double getVelocita() {
        return velocita;
    }

    public void setVelocita(double velocita) {
        this.velocita = velocita;
    }

    public Coordinate getCoordinate(){
        return new Coordinate(this.x,this.y);
    }
}
