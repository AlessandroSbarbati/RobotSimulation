import controller.ControllerRobot;
import controller.ShapeDataHandler;
import model.*;
import simulator.SimulatorImpl;
import utils.FollowMeParser;
import utils.RobotCommand;

import java.util.HashMap;

public class MainEsempio1 {

    public static void main(String[] args) {
        // Creazione del controller dei robot
        ControllerRobot controllerRobot = new ControllerRobot();

        // Creazione di un robot con un comando base
        Robot robot1 = new Robot(0, 0, 0);
        controllerRobot.addCommand(robot1, RobotCommand.MOVE);

        // Creazione di un secondo robot con un altro comando base
        Robot robot2 = new Robot(5, 5, 0);
        controllerRobot.addCommand(robot2, RobotCommand.STOP);

        // Creazione di uno ShapeDataHandler utilizzando un FollowMeParser
        FollowMeParser parser = new FollowMeParser(null);
        ShapeDataHandler<RobotInterface> shapeDataHandler = new ShapeDataHandler<>(parser);

        // Specifica del percorso del file di configurazione dello spazio
        String configurazioneSpazioPath = "C:\\Users\\39327\\IdeaProjects\\RobotSimulation\\src\\main\\java\\aree";

        // Creazione di una mappa con le coordinate iniziali dei robot
        HashMap<Robot, Coordinate> robotCoordinatesHashMap = new HashMap<>();
        robotCoordinatesHashMap.put(robot1, new Coordinate(0, 0));
        robotCoordinatesHashMap.put(robot2, new Coordinate(5, 5));

        // Creazione dello spazio utilizzando ShapeDataHandler
        InfiniteSurface<Robot, Area> spazio = shapeDataHandler.generaSpazio(configurazioneSpazioPath, robotCoordinatesHashMap);

        // Creazione del simulatore con il controller dei robot e lo spazio appena creato
        SimulatorImpl simulator = new SimulatorImpl(controllerRobot, spazio);

        // Avvio della simulazione con un intervallo di tempo di 2 secondi e una durata totale di 8 secondi
        simulator.simulate(2.0, 8.0);
    }
}
