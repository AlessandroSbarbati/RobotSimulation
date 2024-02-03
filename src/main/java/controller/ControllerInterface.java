package controller;

import model.*;
import utils.RobotCommand;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Questa interfaccia serve per dare una struttura al ControllerRobot
 */
public interface ControllerInterface {
    public void addCommand(Robot robot, RobotCommand command);
    public void executeCommandLoop(Robot robot, RobotCommand command, int n,boolean flag, ShapeData shape);
    public void executeCommand(Robot robot, RobotCommand command, CoordinateRobot coord, Coordinate coordArrivo, String etichetta, int s);
    public HashMap<Robot, ArrayList<RobotCommand>> getMappaComandiRobot();
    }
