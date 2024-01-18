package simulator;

import controller.ControllerRobot;
import model.InfiniteSurface;
import model.RectangularArea;
import model.Robot;
import utils.RobotCommand;

import java.util.ArrayList;

public class SimulationScenario2{

    public static void addCustomCommandsForScenario2(SimulatorImpl simulator) {
        for (Robot robot : simulator.getRobots()) {
            ControllerRobot controller = new ControllerRobot(robot, simulator.getInfiniteSurface(), simulator);

            // Esegui le azioni specificate per lo scenario 2
            controller.executeCommand(RobotCommand.MOVERANDOM, new double[]{-10, 10, -10, 10, 5}, "");
            controller.executeCommand(RobotCommand.STOP, new double[]{}, "");
            controller.executeCommand(RobotCommand.MOVE, new double[]{5, 0, 3}, "");
            controller.executeCommand(RobotCommand.STOP, new double[]{}, "");
            controller.executeCommand(RobotCommand.SIGNAL, new double[]{}, "CustomSignal");
            controller.executeCommand(RobotCommand.MOVERANDOM, new double[]{-10, 10, -10, 10, 5}, "");
            controller.executeCommand(RobotCommand.STOP, new double[]{}, "");
            controller.executeCommand(RobotCommand.UNSIGNAL, new double[]{}, "CustomSignal");
        }
    }

}
