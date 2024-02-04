package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * La classe InfiniteSurface rappresenta un'area piana di dimensioni illimitate che può contenere forme circolari e rettangolari.
 */
public class InfiniteSurface<R extends RobotInterface,A extends Area> implements Surface {

    private final ArrayList<A> containedAreas;
    private final HashMap<R, Coordinate> containedRobots;

    public InfiniteSurface(ArrayList<A> aree, HashMap<R, Coordinate> robots) {
        this.containedAreas = aree;
        this.containedRobots = robots;
    }

    public ArrayList<A> getContainedAreas() {
        return containedAreas;
    }

    public HashMap<R, Coordinate> getContainedRobots() {
        return containedRobots;
    }

    public Coordinate getCoordinateRobot(RobotInterface robot){
        return this.containedRobots.get(robot);
    }

    public List<String> areaChekerRobot(R robot){
        return containedAreas.stream()
                .filter(aree -> aree.areaChecker(getCoordinateRobot(robot)))
                .map(Area::getEtichetta).toList();
    }
}