import controller.ControllerRobot;
import model.*;
import utils.RobotCommand;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


class ControllerRobotTest {

    @Test
    void addCommandTest() {
        ControllerRobot controllerRobot = new ControllerRobot();
        Robot robot = new Robot(0, 0, 1);
        RobotCommand command = RobotCommand.MOVE;

        assertDoesNotThrow(() -> controllerRobot.addCommand(robot, command));

        HashMap<Robot, ArrayList<RobotCommand>> mappaComandi = controllerRobot.getMappaComandiRobot();
        assertTrue(mappaComandi.containsKey(robot));
        assertTrue(mappaComandi.get(robot).contains(command));
    }

    @Test
    void executeCommandTest() {
        ControllerRobot controllerRobot = new ControllerRobot();
        Robot robot = new Robot(0, 0, 1);
        RobotCommand command = RobotCommand.MOVE;
        CoordinateRobot coord = new CoordinateRobot(0, 0, 1);
        Coordinate coordArrivo = new Coordinate(2, 3);
        String etichetta = "condizione";
        int s = 5;

        assertDoesNotThrow(() -> controllerRobot.executeCommand(robot, command, coord, coordArrivo, etichetta, s));

        HashMap<Robot, ArrayList<RobotCommand>> mappaComandi = controllerRobot.getMappaComandiRobot();
        assertTrue(mappaComandi.containsKey(robot));
        assertTrue(mappaComandi.get(robot).contains(command));
    }
}
