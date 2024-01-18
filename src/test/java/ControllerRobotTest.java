import static org.junit.jupiter.api.Assertions.*;

import controller.ControllerRobot;
import model.Area;
import model.InfiniteSurface;
import model.RectangularArea;
import model.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import simulator.AreaChecker;
import simulator.RobotUpdater;
import simulator.SimulatorImpl;
import utils.RobotCommand;

import java.util.ArrayList;
import java.util.List;

class ControllerRobotTest {

    private ControllerRobot controllerRobot;
    private InfiniteSurface infiniteSurface;
    private SimulatorImpl simulator;

    private List<Robot> robots;
    private List<Area> areas;
    private RobotUpdater robotUpdater;
    private AreaChecker areaChecker;

    @BeforeEach
    void setUp() {
        // Inizializza un nuovo ControllerRobot con le dipendenze necessarie
        Robot robot = new Robot(0, 0, 0.1);
        infiniteSurface = new InfiniteSurface();
        simulator = new SimulatorImpl(robots, infiniteSurface);

        // Aggiungi il robot alla simulazione
        simulator.getRobots().add(robot);

        // Crea un'area rettangolare nell'infiniteSurface
        RectangularArea robotArea = new RectangularArea(0, 0, 100, 100);
        infiniteSurface.addArea(robotArea);

        // Inizializza il ControllerRobot
        controllerRobot = new ControllerRobot(robot, infiniteSurface, simulator);
    }

    @Test
    void testMove() {
        // Implementa il test per il metodo move
        double[] args = {10, 20, 5}; // Sostituisci con i valori corretti
        controllerRobot.move(args);

        // Verifica che il robot si sia fermato correttamente
        assertFalse(controllerRobot.getRobot().isMoving());
        assertTrue(controllerRobot.getRobot().hasCondition("Stopped"));
    }

    @Test
    void testMoveRandom() {
        // Implementa il test per il metodo moveRandom
        double[] args = {0, 50, 0, 50, 5}; // Sostituisci con i valori corretti
        controllerRobot.moveRandom(args);

        // Verifica che il robot si sia fermato correttamente
        assertFalse(controllerRobot.getRobot().isMoving());
        assertTrue(controllerRobot.getRobot().hasCondition("Stopped"));
    }

    @Test
    void testSignal() {
        // Implementa il test per il metodo signal
        String label = "TestSignal"; // Sostituisci con l'etichetta corretta
        controllerRobot.signal(label);

        // Verifica che il robot abbia correttamente la condizione aggiunta
        assertTrue(controllerRobot.getRobot().hasCondition(label));
    }

    @Test
    void testFollow() {
        // Implementa il test per il metodo follow
        String label = "FollowCondition";
        double[] args = {5}; // Sostituisci con i valori corretti
        controllerRobot.follow(label, args);

        // Verifica che il robot si sia fermato correttamente
        assertFalse(controllerRobot.getRobot().isMoving());
        assertTrue(controllerRobot.getRobot().hasCondition("Stopped") || controllerRobot.getRobot().hasCondition(label));
    }

    @Test
    void testStop() {
        // Implementa il test per il metodo stop
        controllerRobot.stop();

        // Verifica che il robot si sia fermato correttamente
        assertFalse(controllerRobot.getRobot().isMoving());
        assertTrue(controllerRobot.getRobot().hasCondition("Stopped"));
    }

    @Test
    void testContinueCommand() {
        // Implementa il test per il metodo continueCommand
        long seconds = 3;
        double[] moveArgs = {10, 20, 5}; // Sostituisci con i valori corretti
        controllerRobot.continueCommand(seconds, moveArgs);

        // Verifica che il robot si sia fermato correttamente
        assertFalse(controllerRobot.getRobot().isMoving());
        assertTrue(controllerRobot.getRobot().hasCondition("Stopped"));
    }

    @Test
    void testRepeatCommand() {
        // Implementa il test per il metodo repeatCommand
        int iterations = 2;
        List<RobotCommand> commands = new ArrayList<>();
        commands.add(RobotCommand.MOVE);
        commands.add(RobotCommand.STOP);
        controllerRobot.setListCommand(commands);
        controllerRobot.repeatCommand(iterations);

        // Verifica che il robot si sia fermato correttamente
        assertFalse(controllerRobot.getRobot().isMoving());
        assertTrue(controllerRobot.getRobot().hasCondition("Stopped"));
    }

    @Test
    void testUntil() {
        // Implementa il test per il metodo until
        String label = "ConditionToWait";
        List<RobotCommand> commands = new ArrayList<>();
        commands.add(RobotCommand.SIGNAL);
        commands.add(RobotCommand.MOVE);
        controllerRobot.setListCommand(commands);

        // Avvia until in un thread separato poiché aspetta una condizione specifica
        new Thread(() -> controllerRobot.until(label)).start();

        // Verifica che il robot si sia fermato correttamente
        assertFalse(controllerRobot.getRobot().isMoving());
        assertTrue(controllerRobot.getRobot().hasCondition("Stopped") || controllerRobot.getRobot().hasCondition(label));
    }

    @Test
    void testDoForever() {
        // Implementa il test per il metodo doForever
        List<RobotCommand> commands = new ArrayList<>();
        commands.add(RobotCommand.MOVE);
        commands.add(RobotCommand.STOP);
        controllerRobot.setListCommand(commands);
        controllerRobot.doForever();

        // Verifica che il robot si sia fermato correttamente
        assertFalse(controllerRobot.getRobot().isMoving());
        assertTrue(controllerRobot.getRobot().hasCondition("Stopped"));
    }

    @Test
    void testDone() {
        // Implementa il test per il metodo done
        controllerRobot.done();

        // Verifica che il robot non sia in movimento e che non abbia condizioni aggiunte
        assertFalse(controllerRobot.getRobot().isMoving());
        assertTrue(controllerRobot.getRobot().getConditions().isEmpty());
    }

    @Test
    void testSimulateMovement() {
        // Implementa il test per il metodo simulateMovement
        double targetX = 20;
        double targetY = 30;
        double time = 2;
        controllerRobot.simulateMovement(targetX, targetY, time);

        // Verifica che il robot si sia fermato correttamente
        assertFalse(controllerRobot.getRobot().isMoving());
        assertTrue(controllerRobot.getRobot().hasCondition("Stopped"));
    }

    @Test
    void testHandleInteraction() {
        // Implementa il test per il metodo handleInteraction
        Robot robot = new Robot(0,0,0.1);
        Area area = new RectangularArea(0, 0, 50, 50); // Sostituisci con i valori corretti
        controllerRobot.handleInteraction(robot, area);

        // Verifica che il robot si sia fermato correttamente
        assertFalse(robot.isMoving());
        assertTrue(robot.hasCondition("Stopped"));
    }

}
