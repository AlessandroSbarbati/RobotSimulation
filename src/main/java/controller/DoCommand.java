package controller;

import model.Coordinate;
import model.CoordinateRobot;
import model.Robot;
import model.RobotInterface;
import utils.RobotCommand;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Questa interfaccia serve per dare una struttura alla classe ComandiRobotBase
 */
public interface DoCommand {
    public void doCommand(HashMap<Robot, ArrayList<RobotCommand>>mappa, Robot robot, RobotCommand command,
                          CoordinateRobot coord, Coordinate coordArrivo, String etichetta,int s);
}
