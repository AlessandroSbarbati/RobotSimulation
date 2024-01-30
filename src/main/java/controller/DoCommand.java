package controller;

import model.CoordinateRobot;
import model.Robot;
import utils.RobotCommand;

public interface DoCommand {
    public void doCommand(Robot robot, RobotCommand command, CoordinateRobot coord, String etichetta);
}
