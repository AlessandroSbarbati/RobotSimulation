package simulator;

import controller.ControllerRobot;
import model.CircularArea;
import model.Robot;
import utils.RobotCommand;

public class SimulationScenario1 {

    public static void addCustomCommandsForScenario1(SimulatorImpl simulator) {
        for (Robot robot : simulator.getRobots()) {
            ControllerRobot controller = new ControllerRobot(robot, simulator.getInfiniteSurface(), simulator);

            // Esegui le azioni specificate
            controller.executeCommand(RobotCommand.MOVE, new double[]{10, 0, 5}, "");
            controller.executeCommand(RobotCommand.STOP, new double[]{}, "");
            controller.executeCommand(RobotCommand.SIGNAL, new double[]{}, "CustomSignal");
            controller.executeCommand(RobotCommand.MOVE, new double[]{5, 0, 3}, "");
            controller.executeCommand(RobotCommand.STOP, new double[]{}, "");
            controller.executeCommand(RobotCommand.UNSIGNAL, new double[]{}, "CustomSignal");
        }
    }
}
