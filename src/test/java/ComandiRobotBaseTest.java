import controller.ComandiRobotBase;
import model.Coordinate;
import model.CoordinateRobot;
import model.Robot;
import model.RobotInterface;
import org.junit.jupiter.api.Test;
import utils.RobotCommand;

import java.util.ArrayList;
import java.util.HashMap;
import static org.junit.jupiter.api.Assertions.*;

class ComandiRobotBaseTest {

    @Test
    void moveTest() {
        ComandiRobotBase comandiRobotBase = new ComandiRobotBase();
        CoordinateRobot coord = new CoordinateRobot(0, 0, 1);
        Coordinate coordArrivo = new Coordinate(2, 3);

        HashMap<Robot, ArrayList<RobotCommand>> mappa = new HashMap<>();
        Robot robot = new Robot(0, 0, 1);
        mappa.put(robot, new ArrayList<>());

        HashMap<Robot, ArrayList<RobotCommand>> result = comandiRobotBase.move(coord, coordArrivo, mappa);

        assertNotNull(result);
        assertEquals(2.0, robot.getCoordinate().getX());
        assertEquals(3.0, robot.getCoordinate().getY());
    }

    @Test
    void moveRandomTest() {
        ComandiRobotBase comandiRobotBase = new ComandiRobotBase();
        CoordinateRobot coord = new CoordinateRobot(0, 0, 1);
        Coordinate coordArrivo = new Coordinate(2, 3);

        HashMap<Robot, ArrayList<RobotCommand>> mappa = new HashMap<>();
        Robot robot = new Robot(0, 0, 1);
        mappa.put(robot, new ArrayList<>());

        HashMap<Robot, ArrayList<RobotCommand>> result = comandiRobotBase.moveRandom(mappa, coord, coordArrivo);

        assertNotNull(result);
        assertNotEquals(0.0, robot.getCoordinate().getX());
        assertNotEquals(0.0, robot.getCoordinate().getY());
    }

    @Test
    void signalTest() {
        ComandiRobotBase comandiRobotBase = new ComandiRobotBase();
        Robot robot = new Robot(0, 0, 1);

        assertThrows(IllegalArgumentException.class, () -> comandiRobotBase.signal(robot, "!invalidLabel"));

        String validLabel = "validLabel";
        Robot result = comandiRobotBase.signal(robot, validLabel);

        assertTrue(result.hasCondition(validLabel));
    }

    @Test
    void validLabelTest() {
        ComandiRobotBase comandiRobotBase = new ComandiRobotBase();

        assertTrue(comandiRobotBase.validLabel("validLabel"));
        assertFalse(comandiRobotBase.validLabel("!invalidLabel"));
    }
    @Test
    void calcoloIncrementoTest() {
        ComandiRobotBase comandiRobotBase = new ComandiRobotBase();
        CoordinateRobot coord = new CoordinateRobot(0, 0, 1);
        Coordinate coordArrivo = new Coordinate(2, 3);

        ArrayList<Double> result = comandiRobotBase.calcoloIncremento(coord, coordArrivo);

        assertNotNull(result);
        assertEquals(0.894427, result.get(0), 0.000001); // Controlla l'approssimazione a 6 decimali
        assertEquals(0.447214, result.get(1), 0.000001);
    }
    @Test
    void unsignalTest() {
        ComandiRobotBase comandiRobotBase = new ComandiRobotBase();
        Robot robot = new Robot(0, 0, 1);

        robot.addCondition("etichetta");

        Robot result = comandiRobotBase.unsignal(robot, "etichetta");

        assertFalse(result.hasCondition("etichetta"));
    }
    @Test
    void stopTest() {
        ComandiRobotBase comandiRobotBase = new ComandiRobotBase();
        HashMap<Robot, ArrayList<RobotCommand>> mappa = new HashMap<>();
        Robot robot = new Robot(0, 0, 1);
        mappa.put(robot, new ArrayList<>());

        HashMap<Robot, ArrayList<RobotCommand>> result = comandiRobotBase.stop(mappa);

        assertNotNull(result);
        assertEquals(0.0, robot.getCoordinate().getVelocita());
    }
    @Test
    void followTest() {
        ComandiRobotBase comandiRobotBase = new ComandiRobotBase();
        HashMap<Robot, ArrayList<RobotCommand>> mappa = new HashMap<>();
        Robot robot1 = new Robot(1, 1, 1);
        Robot robot2 = new Robot(2, 2, 1);
        robot1.addCondition("etichetta");
        robot2.addCondition("etichetta");
        mappa.put(robot1, new ArrayList<>());
        mappa.put(robot2, new ArrayList<>());

        HashMap<Robot, ArrayList<RobotCommand>> result = comandiRobotBase.follow(mappa, "etichetta");

        assertNotNull(result);
        assertEquals(1.5, robot1.getCoordinate().getX());
        assertEquals(1.5, robot1.getCoordinate().getY());
        assertEquals(1.5, robot2.getCoordinate().getX());
        assertEquals(1.5, robot2.getCoordinate().getY());
    }
    @Test
    void continueCommandTest() {
        ComandiRobotBase comandiRobotBase = new ComandiRobotBase();
        HashMap<Robot, ArrayList<RobotCommand>> mappa = new HashMap<>();
        Robot robot = new Robot(0, 0, 1);
        mappa.put(robot, new ArrayList<>());

        HashMap<Robot, ArrayList<RobotCommand>> result = comandiRobotBase.continueCommand(3, mappa, new CoordinateRobot(0, 0, 1), new Coordinate(2, 3));

        assertNotNull(result);
    }
    @Test
    void ritornaRobotMoveTest() {
        ComandiRobotBase comandiRobotBase = new ComandiRobotBase();
        HashMap<Robot, ArrayList<RobotCommand>> mappa = new HashMap<>();
        Robot robot = new Robot(0, 0, 1);
        mappa.put(robot, new ArrayList<>());

        HashMap<Robot, ArrayList<RobotCommand>> result = comandiRobotBase.ritornaRobotMove(mappa, new CoordinateRobot(0, 0, 1), new Coordinate(2, 3));

        assertNotNull(result);
    }
    @Test
    void aggiornaMappaTest() {
        ComandiRobotBase comandiRobotBase = new ComandiRobotBase();
        HashMap<Robot, ArrayList<RobotCommand>> mappa = new HashMap<>();
        Robot robot = new Robot(0, 0, 1);
        mappa.put(robot, new ArrayList<>());

        HashMap<Robot, ArrayList<RobotCommand>> result = comandiRobotBase.aggiornaMappa(mappa, new CoordinateRobot(2, 3, 1), new CoordinateRobot(0, 0, 1));

        assertNotNull(result);
        assertFalse(result.containsKey(robot));
        assertTrue(result.containsKey(new Robot(2, 3, 1)));
    }
}
