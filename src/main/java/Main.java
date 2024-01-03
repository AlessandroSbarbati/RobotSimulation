import model.Area;
import model.Robot;
import model.CircularArea;
import simulator.Simulator;
import simulator.SimulatorInterface;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Creazione di oggetti Robot e Aree
        Robot robot1 = new Robot(0, 0, 1.0);
        Robot robot2 = new Robot(5, 0, 0.5);

        CircularArea area1 = new CircularArea(2, 2, 3);
        CircularArea area2 = new CircularArea(6, 0, 2);

        // Aggiunta dei robot e delle aree alla lista
        List<Robot> robots = new ArrayList<>();
        robots.add(robot1);
        robots.add(robot2);

        List<Area> areas = new ArrayList<>();
        areas.add(area1);
        areas.add(area2);

        // Creazione del simulatore
        SimulatorInterface simulator = new Simulator(robots, areas);

        // Esecuzione della simulazione
        simulator.simulate(0.1, 10.0);
    }
}
