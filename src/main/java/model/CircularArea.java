package model;

/**
 * La classe CircularArea rappresenta un'area circolare definita da un centro e un raggio.
 */
public class CircularArea implements Area {

    private final double centerX;
    private final double centerY;
    private final double radius;

    /**
     * Costruttore per CircularArea.
     *
     * @param centerX La coordinata x del centro.
     * @param centerY La coordinata y del centro.
     * @param radius  Il raggio dell'area circolare (deve essere positivo).
     * @throws IllegalArgumentException Se il raggio non è positivo.
     */
    public CircularArea(double centerX, double centerY, double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Il raggio deve essere positivo.");
        }

        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    /**
     * Verifica se un punto con le coordinate specificate appartiene all'area circolare.
     *
     * @param x La coordinata x del punto.
     * @param y La coordinata y del punto.
     * @return True se il punto appartiene all'area circolare, altrimenti False.
     */
    @Override
    public boolean contains(double x, double y) {
        double distanceSquared = Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2);
        return distanceSquared <= Math.pow(radius, 2);
    }

    /**
     * Restituisce il valore massimo della coordinata x dell'area circolare.
     *
     * @return Il valore massimo della coordinata x.
     */
    @Override
    public double getMaxX() {
        return centerX + radius;
    }

    /**
     * Restituisce il valore minimo della coordinata x dell'area circolare.
     *
     * @return Il valore minimo della coordinata x.
     */
    @Override
    public double getMinX() {
        return centerX - radius;
    }

    /**
     * Restituisce il valore massimo della coordinata y dell'area circolare.
     *
     * @return Il valore massimo della coordinata y.
     */
    @Override
    public double getMaxY() {
        return centerY + radius;
    }

    /**
     * Restituisce il valore minimo della coordinata y dell'area circolare.
     *
     * @return Il valore minimo della coordinata y.
     */
    @Override
    public double getMinY() {
        return centerY - radius;
    }
    /**
     * Restituisce la coordinata x del centro dell'area circolare.
     *
     * @return Il valore della coordinata x del centro.
     */
    public double getCenterX() {
        return centerX;
    }

    /**
     * Restituisce la coordinata y del centro dell'area circolare.
     *
     * @return Il valore della coordinata y del centro.
     */
    public double getCenterY() {
        return centerY;
    }

    /**
     * Restituisce il raggio dell'area circolare.
     *
     * @return Il valore del raggio dell'area circolare.
     */
    public double getRadius() {
        return radius;
    }

    /**
     * Restituisce un array contenente i valori di centerX, centerY e radius dell'area circolare.
     *
     * @return Un array di double contenente i valori di centerX, centerY e radius, nell'ordine.
     */
    public double[] getParameters() {
        return new double[]{centerX, centerY, radius};
    }

}
