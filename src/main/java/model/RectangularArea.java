package model;

/**
 * La classe RectangularArea rappresenta un'area rettangolare definita da una posizione in alto a sinistra,
 * larghezza e altezza.
 */
public class RectangularArea implements Area {

    private final double topLeftX;
    private final double topLeftY;
    private final double width;
    private final double height;

    /**
     * Costruttore per RectangularArea.
     *
     * @param topLeftX La coordinata x del vertice in alto a sinistra.
     * @param topLeftY La coordinata y del vertice in alto a sinistra.
     * @param width    La larghezza dell'area rettangolare (deve essere positiva).
     * @param height   L'altezza dell'area rettangolare (deve essere positiva).
     * @throws IllegalArgumentException Se larghezza o altezza non sono positive.
     */
    public RectangularArea(double topLeftX, double topLeftY, double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Larghezza e altezza devono essere entrambe positive.");
        }

        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.width = width;
        this.height = height;
    }

    /**
     * Verifica se un punto con le coordinate specificate appartiene all'area rettangolare.
     *
     * @param x La coordinata x del punto.
     * @param y La coordinata y del punto.
     * @return True se il punto appartiene all'area rettangolare, altrimenti False.
     */
    @Override
    public boolean contains(double x, double y) {
        return x >= topLeftX && x <= topLeftX + width && y >= topLeftY && y <= topLeftY + height;
    }

    /**
     * Restituisce il valore massimo della coordinata x dell'area rettangolare.
     *
     * @return Il valore massimo della coordinata x.
     */
    @Override
    public double getMaxX() {
        return topLeftX + width;
    }

    /**
     * Restituisce il valore minimo della coordinata x dell'area rettangolare.
     *
     * @return Il valore minimo della coordinata x.
     */
    @Override
    public double getMinX() {
        return topLeftX;
    }

    /**
     * Restituisce il valore massimo della coordinata y dell'area rettangolare.
     *
     * @return Il valore massimo della coordinata y.
     */
    @Override
    public double getMaxY() {
        return topLeftY + height;
    }

    /**
     * Restituisce il valore minimo della coordinata y dell'area rettangolare.
     *
     * @return Il valore minimo della coordinata y.
     */
    @Override
    public double getMinY() {
        return topLeftY;
    }
}
