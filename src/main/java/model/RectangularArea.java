package model;

/**
 * La classe RectangularArea rappresenta un'area rettangolare.
 */
public class RectangularArea implements Area {
    private final Coordinate coordinate;
    private final String etichetta;
    private final double altezza;
    private final double larghezza;
    /**
     * Costrutture con coordinate ed etichetta.
     * @param coord coordinate della figura.
     * @param etichetta etichetta della figura.
     */
    public RectangularArea(double[] coord, String etichetta){
        this.coordinate=new Coordinate(coord[0], coord[1]);
        this.etichetta=etichetta;
        this.altezza= coord[1];
        this.larghezza= coord[2];
    }
    /**
     * Costruttore con shape
     * @param area shape
     */
    public RectangularArea(ShapeData area) {
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
        double angoloDxBassoX = coordinate.getX() + larghezza / 2;
        double angoloDxBassoY = coordinate.getY() - altezza / 2;
        double angoloSxAltoX = coordinate.getX() - larghezza / 2;
        double angoloSxAltoY = coordinate.getY() + altezza / 2;
        return angoloSxAltoX <= coordinateRobot.getX() && coordinateRobot.getX()
                <= angoloDxBassoX && angoloDxBassoY
                <= coordinateRobot.getY() && coordinateRobot.getY()
                <= angoloSxAltoY;
    }

    public double getAltezza(){
        return this.altezza;
    }

    public double getLarghezza(){
        return this.larghezza;
    }
}
