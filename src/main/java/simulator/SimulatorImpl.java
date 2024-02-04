package simulator;

import controller.ControllerRobot;
import model.Coordinate;
import model.InfiniteSurface;
import model.Robot;
import model.ShapeData;
import utils.RobotCommand;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Implementazione della simulazione che utilizza un controller per gestire i robot.
 */
public class SimulatorImpl implements Simulator {

    private final ControllerRobot controllerRobot;
    private boolean isSimulationRunning;
    private InfiniteSurface infiniteSurface;


    public SimulatorImpl(ControllerRobot controllerRobot, InfiniteSurface infiniteSurface) {
        this.controllerRobot = controllerRobot;
        this.isSimulationRunning = false;
        this.infiniteSurface = infiniteSurface;
    }
    /**
     * Avvia la simulazione eseguendo i comandi dei robot per un certo periodo di tempo.
     *
     * @param dt   L'intervallo di tempo tra due iterazioni della simulazione.
     * @param time La durata totale della simulazione.
     */
    @Override
    public void simulate(double dt, double time) {
        double currentTime = 0;
        this.isSimulationRunning = true;
        while (currentTime < time) {
            HashMap<Robot, ArrayList<RobotCommand>> mappaComandiRobot = controllerRobot.getMappaComandiRobot();

            for (Robot robot : mappaComandiRobot.keySet()) {
                ArrayList<RobotCommand> comandi = mappaComandiRobot.get(robot);
                for (RobotCommand comando : comandi) {
                    if (comando.isLoopCommand()) {
                        // Esegui il comando loop
                        ShapeData shape = null;
                        controllerRobot.executeCommandLoop(robot, comando, 3, true, shape);
                    } else {
                        // Esegui il comando base
                        controllerRobot.executeCommand(robot, comando, robot.getCoordinate(), new Coordinate(10, 10), "Condizione", 5);
                    }
                }
            }

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
