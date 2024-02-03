package model;

/**
 * La classe CircularArea rappresenta un'area circolare.
 */
public class CircularArea implements Area {
    private final Coordinate coordinate;
    private final String etichetta;
    private final double raggio;

    /**
     * Costrutture con coordinate ed etichetta.
     * @param coord coordinate della figura.
     * @param etichetta etichetta della figura.
     */
    public CircularArea(double[] coord,String etichetta){
        this.coordinate=new Coordinate(coord[0],coord[1]);
        this.etichetta=etichetta;
        this.raggio=coord[2];
    }

    /**
     * Costruttore con shape
     * @param area shape
     */
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

    /**
     * Questo metodo serve per controllare se ci sono robot all'interno dell'area.
     * @param coordinateRobot coordinate dei robot.
     * @return robot all'interno dell'area.
     */
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
