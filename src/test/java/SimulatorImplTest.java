import model.*;
import org.junit.jupiter.api.Test;
import simulator.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimulatorImplTest {

    @Test
    void createRandomRobots_validInput_createsRobots() {
        // Arrange
        List<Robot> robots = new ArrayList<>();
        InfiniteSurface infiniteSurface = new InfiniteSurface();
        SimulatorImpl simulator = new SimulatorImpl(robots, infiniteSurface);
        CircularArea circularArea = new CircularArea(0, 0, 10);

        // Act
        simulator.createRandomRobots(5, circularArea);

        // Assert
        assertEquals(5, robots.size());
        for (Robot robot : robots) {
            assertTrue(circularArea.contains(robot.getX(), robot.getY()));
        }
    }

    @Test
    void findRobotByLabel_existingLabel_returnsRobot() {
        // Arrange
        List<Robot> robots = new ArrayList<>();
        InfiniteSurface infiniteSurface = new InfiniteSurface();
        SimulatorImpl simulator = new SimulatorImpl(robots, infiniteSurface);
        Robot robot = new Robot(1, 2, 3);
        robot.addCondition("Label");
        robots.add(robot);

        // Act
        Robot result = simulator.findRobotByLabel("Label");

        // Assert
        assertNotNull(result);
        assertEquals(robot, result);
    }

    @Test
    void findRobotByLabel_nonexistentLabel_returnsNull() {
        // Arrange
        List<Robot> robots = new ArrayList<>();
        InfiniteSurface infiniteSurface = new InfiniteSurface();
        SimulatorImpl simulator = new SimulatorImpl(robots, infiniteSurface);

        // Act
        Robot result = simulator.findRobotByLabel("NonexistentLabel");

        // Assert
        assertNull(result);
    }

    @Test
    void findRobotsByCondition_existingCondition_returnsRobots() {
        // Arrange
        List<Robot> robots = new ArrayList<>();
        InfiniteSurface infiniteSurface = new InfiniteSurface();
        SimulatorImpl simulator = new SimulatorImpl(robots, infiniteSurface);
        Robot robot1 = new Robot(1, 2, 3);
        Robot robot2 = new Robot(4, 5, 6);
        robot1.addCondition("Condition");
        robot2.addCondition("Condition");
        robots.add(robot1);
        robots.add(robot2);

        // Act
        List<Robot> result = simulator.findRobotsByCondition("Condition");

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(robot1));
        assertTrue(result.contains(robot2));
    }

    @Test
    void findRobotsByCondition_nonexistentCondition_returnsEmptyList() {
        // Arrange
        List<Robot> robots = new ArrayList<>();
        InfiniteSurface infiniteSurface = new InfiniteSurface();
        SimulatorImpl simulator = new SimulatorImpl(robots, infiniteSurface);

        // Act
        List<Robot> result = simulator.findRobotsByCondition("NonexistentCondition");

        // Assert
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void createRandomRobots_invalidArea_throwsException() {
        // Arrange
        List<Robot> robots = new ArrayList<>();
        InfiniteSurface infiniteSurface = new InfiniteSurface();
        SimulatorImpl simulator = new SimulatorImpl(robots, infiniteSurface);
        RectangularArea rectangularArea = new RectangularArea(0, 0, -5, 10);  // Invalid area with negative width

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> simulator.createRandomRobots(5, rectangularArea));
    }

    @Test
    void simulateScenario1_validInput_simulatesScenario() {
        // Arrange
        List<Robot> robots = new ArrayList<>();
        InfiniteSurface infiniteSurface = new InfiniteSurface();
        SimulatorImpl simulator = new SimulatorImpl(robots, infiniteSurface);
        SimulationScenario1.addCustomCommandsForScenario1(simulator);

        // Act
        simulator.simulate(0.1, 1.0);

        // Assert
        for (Robot robot : robots) {
            assertTrue(robot.getConditions().contains("Interacting with CircularArea"));
            assertFalse(robot.isMoving());
        }
    }

    @Test
    void simulateScenario2_validInput_simulatesScenario() {
        // Arrange
        List<Robot> robots = new ArrayList<>();
        InfiniteSurface infiniteSurface = new InfiniteSurface();
        SimulatorImpl simulator = new SimulatorImpl(robots, infiniteSurface);
        SimulationScenario2.addCustomCommandsForScenario2(simulator);

        // Act
        simulator.simulate(0.1, 1.0);

        // Assert
        for (Robot robot : robots) {
            assertTrue(robot.getConditions().contains("Interacting with RectangularArea"));
            assertFalse(robot.isMoving());
        }
    }
}
