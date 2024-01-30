package model;

public class CoordinateRobot extends Coordinate{
    private double velocita;
   // private final double direzione;

    public CoordinateRobot(double x, double y, double velocita){
        super(x,y);
        /*this.direzione=Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
        this.x=(x/direzione)*velocita;
        this.y=(y/direzione)*velocita;*/
        this.velocita=velocita;
    }

    public double getVelocita() {
        return velocita;
    }

    public void setVelocita(double velocita) {
        this.velocita = velocita;
    }
}
