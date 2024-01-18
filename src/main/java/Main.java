import model.InfiniteSurface;
import model.Robot;
import simulator.SimulationScenario1;
import simulator.SimulationScenario2;
import simulator.SimulatorImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Creazione di una lista di robot
        List<Robot> robots = new ArrayList<>();

        // Creazione di un'area infinita
        InfiniteSurface infiniteSurface = new InfiniteSurface();

        // Creazione del simulatore utilizzando il costruttore specificato
        SimulatorImpl simulator = new SimulatorImpl(robots, infiniteSurface);

        // Esecuzione della simulazione dello scenario 1
        System.out.println("Simulazione Scenario 1:");
        SimulationScenario1.addCustomCommandsForScenario1(simulator);
        simulator.simulate(0.1, 1.0);
        printRobotStatus(simulator.getRobots());

        // Pulizia dei robot e dell'area prima di eseguire la seconda simulazione
        simulator.clear();

        // Esecuzione della simulazione dello scenario 2
        System.out.println("\nSimulazione Scenario 2:");
        SimulationScenario2.addCustomCommandsForScenario2(simulator);
        simulator.simulate(0.1, 1.0);
        printRobotStatus(simulator.getRobots());
    }

    // Metodo per stampare lo stato dei robot
    private static void printRobotStatus(List<Robot> robots) {
        System.out.println("Stato attuale dei robot:");
        for (Robot robot : robots) {
            System.out.println("Robot " + robot.getId() + ": Posizione=(" + robot.getX() + ", " + robot.getY() + "), Velocità=" + robot.getSpeed() + ", Condizioni=" + robot.getConditions());
        }
        System.out.println();
    }
}
