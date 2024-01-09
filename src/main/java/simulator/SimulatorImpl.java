package simulator;

import controller.InteractionHandler;
import model.Area;
import model.Robot;

import java.util.ArrayList;
import java.util.List;


public class SimulatorImpl implements Simulator {

    private final List<Robot> robots;
    private final List<Area> areas;
    private final InteractionHandler interactionHandler;
    private final RobotUpdater robotUpdater;
    private final AreaChecker areaChecker;


    public SimulatorImpl(List<Robot> robots, List<Area> areas, RobotUpdater robotUpdater, AreaChecker areaChecker) {
        this.robots = robots;
        this.areas = areas;
        this.interactionHandler = new InteractionHandler();
        this.robotUpdater = robotUpdater;
        this.areaChecker = areaChecker;
    }

    public void simulate(double dt, double time) {
        for (double currentTime = 0; currentTime < time; currentTime += dt) {
            robotUpdater.updateRobots(robots, dt);
            areaChecker.checkRobotAreaInteraction(robots, areas, interactionHandler);
        }
    }

    public void createRandomRobots(int numRobots, Area area) {
        for (int i = 0; i < numRobots; i++) {
            double randomX = Math.random() * (area.getMaxX() - area.getMinX()) + area.getMinX();
            double randomY = Math.random() * (area.getMaxY() - area.getMinY()) + area.getMinY();
            double randomSpeed = Math.random() * 10;

            Robot robot = new Robot(randomX, randomY, randomSpeed);
            robots.add(robot);
        }
    }

    private Robot findRobotByLabel(String label) {
        return robots.stream()
                .filter(robot -> robot.getConditions().contains(label))
                .findFirst()
                .orElse(null);
    }

    public List<Robot> findRobotsByCondition(String condition) {
        List<Robot> robotsWithCondition = new ArrayList<>();

        for (Robot robot : robots) {
            if (robot.getConditions().contains(condition)) {
                robotsWithCondition.add(robot);
            }
        }
        return robotsWithCondition;
    }
}
