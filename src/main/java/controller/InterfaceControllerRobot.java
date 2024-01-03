package controller;

import model.Area;
import utils.RobotCommand;

public interface InterfaceControllerRobot {

    void executeCommand(RobotCommand command, double[] args, String label);

    void move(double[] args);

    void signal(String label);

    void unsignal(String label);

    void follow(String label, double[] args);

    void stop();

    void continueCommand(int seconds);

    void repeatCommand(int iterations);

    void until(String label);

    void doForever();

    void done();

    void setRobotArea(Area area);

    Area getRobotArea();

}
