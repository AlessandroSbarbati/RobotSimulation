package model;

public interface RobotInterface {
    public void addCondition(String newCondizione);
    public boolean removeCondition(String condizioneRimossa);
    public boolean hasCondition(String checkCondizione);
    public CoordinateRobot getCoordinate();
    public CoordinateRobot setCoordinate(double x, double y, double velocita);
}
