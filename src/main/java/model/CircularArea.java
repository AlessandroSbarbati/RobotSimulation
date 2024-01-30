package model;

/**
 * La classe CircularArea rappresenta un'area circolare definita da un centro e un raggio.
 */
public class CircularArea implements Area {
    private final Coordinate coordinate;
    private final String etichetta;
    private final double raggio;


    public CircularArea(double[] coord,String etichetta){
        this.coordinate=new Coordinate(coord[0],coord[1]);
        this.etichetta=etichetta;
        this.raggio=coord[2];
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
