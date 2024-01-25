package model;

public interface RobotInterface {
    public void addCondition(String newCondizione);
    public void removeCondition(String condizioneRimossa);
    public boolean hasCondition(String checkCondizione);
    public CoordinateRobot getCoordinate();
    public void setCoordinate(double x, double y, double velocita);
}
