package model;

/**
 * Questa interfaccia serve per definire una struttura per le diverse aree.
 */
public interface Area {

    Coordinate getCoordinate();
    String getEtichetta();
    boolean areaChecker(Coordinate coordinateRobot);
}
