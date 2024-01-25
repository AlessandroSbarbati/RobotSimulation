package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * La classe InfiniteSurface rappresenta un'area piana di dimensioni illimitate che può contenere forme circolari e rettangolari.
 */
public class InfiniteSurface<Robot,A extends Area> {

    private final ArrayList<A> containedAreas;
    private HashMap<Robot, Coordinate> containedRobots;

    /**
     * Costruttore per InfiniteSurface.
     */
    public InfiniteSurface(ArrayList<A> aree, HashMap<Robot, Coordinate> robots) {
        this.containedAreas = aree;
        this.containedRobots = robots;
    }

    public ArrayList<A> getContainedAreas() {
        return containedAreas;
    }

    public HashMap<Robot, Coordinate> getContainedRobots() {
        return containedRobots;
    }

    public Coordinate getCoordinateRobot(Robot robot){
        return this.containedRobots.get(robot);
    }

    public List<String> areaChekerRobot(Robot robot){
        return containedAreas.stream()
                .filter(aree -> aree.areaChecker(getCoordinateRobot(robot)))
                .map(Area::getEtichetta).toList();
    }
}