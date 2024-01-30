package model;

/**
 * La classe RectangularArea rappresenta un'area rettangolare definita da una posizione in alto a sinistra,
 * larghezza e altezza.
 */
public class RectangularArea implements Area {
    private final Coordinate coordinate;
    private final String etichetta;
    private final double altezza;
    private final double larghezza;

    public RectangularArea(double[] coord, String etichetta){
        this.coordinate=new Coordinate(coord[0], coord[1]);
        this.etichetta=etichetta;
        this.altezza= coord[1];
        this.larghezza= coord[2];
    }

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
