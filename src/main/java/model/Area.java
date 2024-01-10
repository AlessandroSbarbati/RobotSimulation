package model;

/**
 * L'interfaccia Area rappresenta un'area e definisce i metodi per verificare l'appartenenza
 * di un punto, ottenere i valori massimi e minimi delle coordinate x e y dell'area.
 */
public interface Area {

    /**
     * Verifica se un punto con le coordinate specificate appartiene all'area.
     *
     * @param x La coordinata x del punto.
     * @param y La coordinata y del punto.
     * @return True se il punto appartiene all'area, altrimenti False.
     */
    boolean contains(double x, double y);

    /**
     * Restituisce il valore massimo della coordinata x dell'area.
     *
     * @return Il valore massimo della coordinata x.
     */
    double getMaxX();

    /**
     * Restituisce il valore minimo della coordinata x dell'area.
     *
     * @return Il valore minimo della coordinata x.
     */
    double getMinX();

    /**
     * Restituisce il valore massimo della coordinata y dell'area.
     *
     * @return Il valore massimo della coordinata y.
     */
    double getMaxY();

    /**
     * Restituisce il valore minimo della coordinata y dell'area.
     *
     * @return Il valore minimo della coordinata y.
     */
    double getMinY();
}
