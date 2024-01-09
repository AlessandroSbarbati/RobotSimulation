package controller;

import model.Area;
import model.Robot;
import utils.RobotCommand;

import java.util.List;

public interface InterfaceControllerRobot {

    void executeCommand(RobotCommand command, double[] args, String label);

    void move(double[] args);

    void moveRandom(double[] args);

    void signal(String label);

    void unsignal(String label);

    void follow(String label, double[] args);

    void stop();

    void continueCommand(long seconds,double[] moveArgs);

    void repeatCommand(int iterations);

    void until(String label);

    void doForever();

    void done();

    void setRobotArea(Area area);

    Area getRobotArea();

    Robot getRobot();

    void setListCommand(List<RobotCommand> commands);

}
