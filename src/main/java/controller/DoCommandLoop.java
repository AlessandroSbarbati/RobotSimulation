package controller;

import model.Robot;
import model.RobotInterface;
import model.ShapeData;
import utils.RobotCommand;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Questa interfaccia serve per dare una struttura alla classe ComandiRobotLoop
 */
public interface DoCommandLoop {
    public void doCommandLoop(RobotCommand command, int n, HashMap<Robot, ArrayList<RobotCommand>> mappa,
                              Robot robot, boolean flag, ShapeData shape);
}
