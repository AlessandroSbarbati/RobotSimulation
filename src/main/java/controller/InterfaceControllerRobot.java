package controller;

import model.Robot;
import model.RobotInterface;
import utils.RobotCommand;

import java.util.ArrayList;

/**
 * L'interfaccia InterfaceControllerRobot definisce il contratto per il controllo del comportamento di un robot.
 */
public interface InterfaceControllerRobot {

    public void addCommand(Robot robot, RobotCommand command);


    public void executeCommand(RobotInterface robot, ArrayList<RobotCommand> comandi);
}
