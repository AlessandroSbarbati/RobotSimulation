package model;

/**
 * La classe CircularArea rappresenta un'area circolare definita da un centro e un raggio.
 */
public class CircularArea implements Area {
    private final Coordinate coordinate;
    private final String etichetta;
    private final double raggio;


    public CircularArea(double[] args,String etichetta){
        this.coordinate=new Coordinate(args[0],args[1]);
        this.etichetta=etichetta;
        this.raggio=args[2];
    }
    public CircularArea(ShapeData area) {
        this(area.args(), area.label());
    }
    @Override
    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    @Override
    public String getEtichetta() {
        return this.etichetta;
    }

    @Override
    public boolean areaChecker(Coordinate coordinateRobot) {
        double distance = Math.sqrt(Math.pow(coordinateRobot.getX() - this.coordinate.getX(), 2) +
                Math.pow(coordinateRobot.getY() - this.coordinate.getY(), 2));
        return distance <= this.raggio;
    }

    public double getRaggio() {
        return raggio;
    }
}
