package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * L'interfaccia Surface rappresenta un'area piana contenente forme circolari e rettangolari.
 *
 * @param <R> Tipo di oggetto rappresentante un robot all'interno dell'area.
 * @param <A> Tipo di oggetto rappresentante un'area all'interno dell'area.
 */
public interface Surface<R extends RobotInterface, A extends Area> {

    /**
     * Restituisce la lista delle aree contenute nell'area.
     *
     * @return Lista delle aree contenute nell'area.
     */
    ArrayList<A> getContainedAreas();

    /**
     * Restituisce la mappa dei robot contenuti nell'area con le rispettive coordinate.
     *
     * @return Mappa dei robot con le coordinate associate.
     */
    HashMap<R, Coordinate> getContainedRobots();

    /**
     * Restituisce le coordinate di un robot all'interno dell'area.
     *
     * @param robot Robot di cui si vogliono ottenere le coordinate.
     * @return Coordinate del robot.
     */
    Coordinate getCoordinateRobot(R robot);
}
