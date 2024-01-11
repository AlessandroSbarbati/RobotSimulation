package simulator;

import controller.ControllerRobot;
import controller.DefaultInteractionHandler;
import model.Area;
import model.CircularArea;
import model.RectangularArea;
import model.Robot;
import utils.RobotCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementazione della simulazione del sistema robotico.
 */
public class SimulatorImpl implements Simulator {

    private final List<Robot> robots;
    private final List<Area> areas;
    private final DefaultInteractionHandler interactionHandler;
    private final RobotUpdater robotUpdater;
    private final AreaChecker areaChecker;

    /**
     * Costruttore per SimulatorImpl.
     *
     * @param robots        Lista dei robot presenti nella simulazione.
     * @param areas         Lista delle aree presenti nella simulazione.
     * @param robotUpdater  Implementazione dell'aggiornamento dei robot.
     * @param areaChecker   Implementazione del controllo di interazione tra robot e aree.
     */
    public SimulatorImpl(List<Robot> robots, List<Area> areas, RobotUpdater robotUpdater, AreaChecker areaChecker) {
        this.robots = robots;
        this.areas = areas;
        this.interactionHandler = new DefaultInteractionHandler();
        this.robotUpdater = robotUpdater;
        this.areaChecker = areaChecker;
    }

    /**
     * Esegue la simulazione del sistema per un periodo di tempo specificato.
     *
     * @param dt   Il passo temporale della simulazione.
     * @param time Il tempo totale della simulazione.
     */
    @Override
    public void simulate(double dt, double time) {
        for (double currentTime = 0; currentTime < time; currentTime += dt) {
            robotUpdater.updateRobots(robots, dt);
            areaChecker.checkRobotAreaInteraction(robots, areas, interactionHandler);
        }
    }

    /**
     * Crea un numero specificato di robot con posizioni casuali all'interno di un'area data.
     *
     * @param numRobots Il numero di robot da creare.
     * @param area      L'area in cui posizionare i robot.
     */
    public void createRandomRobots(int numRobots, Area area) {
        for (int i = 0; i < numRobots; i++) {
            double randomX = Math.random() * (area.getMaxX() - area.getMinX()) + area.getMinX();
            double randomY = Math.random() * (area.getMaxY() - area.getMinY()) + area.getMinY();
            double randomSpeed = Math.random() * 10;

            Robot robot = new Robot(randomX, randomY, randomSpeed);
            robots.add(robot);
        }
    }

    /**
     * Trova un robot con una specifica etichetta.
     *
     * @param label L'etichetta da cercare.
     * @return Il robot con l'etichetta specificata, null se non trovato.
     */
    public Robot findRobotByLabel(String label) {
        return robots.stream()
                .filter(robot -> robot.getConditions().contains(label))
                .findFirst()
                .orElse(null);
    }

    /**
     * Trova i robot con una specifica condizione.
     *
     * @param condition La condizione da cercare.
     * @return Lista di robot con la condizione specificata.
     */
    public List<Robot> findRobotsByCondition(String condition) {
        List<Robot> robotsWithCondition = new ArrayList<>();

        for (Robot robot : robots) {
            if (robot.getConditions().contains(condition)) {
                robotsWithCondition.add(robot);
            }
        }
        return robotsWithCondition;
    }

    /**
     * Simula uno scenario specifico con azioni personalizzate per i robot.
     *
     * @param dt   Il passo temporale della simulazione.
     * @param time Il tempo totale della simulazione.
     */
    public void simulateScenario1(double dt, double time) {
        createRandomRobots(5, new CircularArea(0, 0, 10));
        addCustomCommandsForScenario1();
        simulate(dt, time);
    }

    /**
     * Aggiunge comandi personalizzati per lo scenario 1 ai robot.
     */
    private void addCustomCommandsForScenario1() {
        for (Robot robot : robots) {
            ControllerRobot controller = new ControllerRobot(robot, areas.get(0), this);

            // Esegui le azioni specificate
            controller.executeCommand(RobotCommand.MOVE, new double[]{10, 0, 5}, "");
            controller.executeCommand(RobotCommand.STOP, new double[]{}, "");
            controller.executeCommand(RobotCommand.SIGNAL, new double[]{}, "CustomSignal");
            controller.executeCommand(RobotCommand.MOVE, new double[]{5, 0, 3}, "");
            controller.executeCommand(RobotCommand.STOP, new double[]{}, "");
            controller.executeCommand(RobotCommand.UNSIGNAL, new double[]{}, "CustomSignal");
        }
    }

    /**
     * Simula uno scenario specifico con azioni personalizzate per i robot.
     *
     * @param dt   Il passo temporale della simulazione.
     * @param time Il tempo totale della simulazione.
     */
    public void simulateScenario2(double dt, double time) {
        createRandomRobots(3, new RectangularArea(-20, -20, 40, 30));
        addCustomCommandsForScenario2();
        simulate(dt, time);
    }

    /**
     * Aggiunge comandi personalizzati per lo scenario 2 ai robot.
     */
    private void addCustomCommandsForScenario2() {
        for (Robot robot : robots) {
            ControllerRobot controller = new ControllerRobot(robot, areas.get(0), this);

            // Esegui le azioni specificate
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
