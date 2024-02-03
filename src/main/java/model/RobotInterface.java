package model;

/**
 * Questa interfaccia definisce una struttura per i robot.
 */
public interface RobotInterface {
    public void addCondition(String newCondizione);
    public boolean removeCondition(String condizioneRimossa);
    public boolean hasCondition(String checkCondizione);
    public CoordinateRobot getCoordinate();
    public CoordinateRobot setCoordinate(double x, double y, double velocita);
}
