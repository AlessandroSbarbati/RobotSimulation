package model;

public class CoordinateRobot extends Coordinate{
    private double velocita;

    public CoordinateRobot(double x, double y, double velocita){
        super(x,y);
        this.velocita=velocita;
    }

    public double getVelocita() {
        return velocita;
    }

    public void setVelocita(double velocita) {
        this.velocita = velocita;
    }
}
