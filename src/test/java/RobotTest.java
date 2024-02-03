import model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

    @Test
    void addConditionTest() {
        Robot robot = new Robot(new CoordinateRobot(0, 0, 1));
        robot.addCondition("condizione1");

        assertTrue(robot.hasCondition("condizione1"));
        assertFalse(robot.hasCondition("condizione2"));
    }

    @Test
    void removeConditionTest() {
        Robot robot = new Robot(new CoordinateRobot(0, 0, 1));
        robot.addCondition("condizione1");

        assertTrue(robot.removeCondition("condizione1"));
        assertFalse(robot.hasCondition("condizione1"));
    }

    @Test
    void hasConditionTest() {
        Robot robot = new Robot(new CoordinateRobot(0, 0, 1));
        robot.addCondition("condizione1");

        assertTrue(robot.hasCondition("condizione1"));
        assertFalse(robot.hasCondition("condizione2"));
    }

    @Test
    void getCoordinateTest() {
        CoordinateRobot coordinateRobot = new CoordinateRobot(1, 2, 1);
        Robot robot = new Robot(coordinateRobot);

        assertEquals(coordinateRobot, robot.getCoordinate());
    }

    @Test
    void setCoordinateTest() {
        Robot robot = new Robot(new CoordinateRobot(0, 0, 1));
        CoordinateRobot newCoordinate = robot.setCoordinate(2, 3, 2);

        assertEquals(new CoordinateRobot(2, 3, 2), newCoordinate);
        assertEquals(newCoordinate, robot.getCoordinate());
    }
}
