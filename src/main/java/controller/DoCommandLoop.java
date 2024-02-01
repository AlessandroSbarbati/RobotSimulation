package controller;

import model.Robot;
import model.RobotInterface;
import utils.RobotCommand;

import java.util.ArrayList;
import java.util.HashMap;

public interface DoCommandLoop {
    public void doCommandLoop(RobotCommand command, int n, HashMap<RobotInterface, ArrayList<RobotCommand>> mappa, Robot robot,boolean flag);
}
