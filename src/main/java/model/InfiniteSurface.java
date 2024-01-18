package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * La classe InfiniteSurface rappresenta un'area piana di dimensioni illimitate che può contenere forme circolari e rettangolari.
 */
public class InfiniteSurface implements Area {

    private final List<Area> containedAreas;

    /**
     * Costruttore per InfiniteSurface.
     */
    public InfiniteSurface() {
        this.containedAreas = new ArrayList<>();
    }

    /**
     * Aggiunge un'area (circolare o rettangolare) all'area piana di dimensioni illimitate.
     *
     * @param area L'area da aggiungere.
     */
    public void addArea(Area area) {
        containedAreas.add(area);
    }

    /**
     * Restituisce un elenco immutabile delle aree contenute nell'area piana di dimensioni illimitate.
     *
     * @return Un elenco immutabile delle aree contenute.
     */
    public List<Area> getContainedAreas() {
        return Collections.unmodifiableList(containedAreas);
    }

    /**
     * Verifica se un punto con le coordinate specificate appartiene all'area piana di dimensioni illimitate.
     *
     * @param x La coordinata x del punto.
     * @param y La coordinata y del punto.
     * @return True, poiché qualsiasi punto è considerato contenuto in un'area illimitata.
     */
    @Override
    public boolean contains(double x, double y) {
        // L'area è illimitata, quindi qualsiasi punto è considerato contenuto.
        // Verifica inoltre la presenza di eventuali aree interne.
        for (Area area : containedAreas) {
            if (area.contains(x, y)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Restituisce il valore massimo della coordinata x dell'area piana di dimensioni illimitate.
     *
     * @return Un valore arbitrario grande positivo.
     */
    @Override
    public double getMaxX() {
        return Double.POSITIVE_INFINITY;
    }

    /**
     * Restituisce il valore minimo della coordinata x dell'area piana di dimensioni illimitate.
     *
     * @return Un valore arbitrario grande negativo.
     */
    @Override
    public double getMinX() {
        return Double.NEGATIVE_INFINITY;
    }

    /**
     * Restituisce il valore massimo della coordinata y dell'area piana di dimensioni illimitate.
     *
     * @return Un valore arbitrario grande positivo.
     */
    @Override
    public double getMaxY() {
        return Double.POSITIVE_INFINITY;
    }

    /**
     * Restituisce il valore minimo della coordinata y dell'area piana di dimensioni illimitate.
     *
     * @return Un valore arbitrario grande negativo.
     */
    @Override
    public double getMinY() {
        return Double.NEGATIVE_INFINITY;
    }
}
