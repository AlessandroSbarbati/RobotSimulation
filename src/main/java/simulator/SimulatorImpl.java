package simulator;

import controller.ControllerRobot;
import model.Coordinate;
import model.Robot;
import model.RobotInterface;
import model.ShapeData;
import utils.RobotCommand;

import java.util.ArrayList;
import java.util.HashMap;

public class SimulatorImpl implements Simulator {

    private final ControllerRobot controllerRobot;
    private boolean isSimulationRunning;


    public SimulatorImpl(ControllerRobot controllerRobot) {
        this.controllerRobot = controllerRobot;
        this.isSimulationRunning = false;
    }

    @Override
    public void simulate(double dt, double time) {
        double currentTime = 0;
        this.isSimulationRunning = true;
        while (currentTime < time) {
            HashMap<Robot, ArrayList<RobotCommand>> mappaComandiRobot = controllerRobot.getMappaComandiRobot();

            // La tua logica per eseguire i comandi e aggiornare lo stato del sistema va qui
            for (Robot robot : mappaComandiRobot.keySet()) {
                ArrayList<RobotCommand> comandi = mappaComandiRobot.get(robot);
                for (RobotCommand comando : comandi) {
                    if (comando.isLoopCommand()) {
                        // Esegui il comando loop
                        ShapeData shape = null;
                        controllerRobot.executeCommandLoop(robot, comando, 3, true, shape);
                    } else {
                        // Esegui il comando base
                        controllerRobot.executeCommand(robot, comando, robot.getCoordinate(), new Coordinate(10, 10), "example_condition", 5);
                    }
                }
            }

            // Incrementa il tempo passando al passo temporale successivo
            currentTime += dt;

            try {
                Thread.sleep((long) (dt * 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.isSimulationRunning = false;
    }

}
