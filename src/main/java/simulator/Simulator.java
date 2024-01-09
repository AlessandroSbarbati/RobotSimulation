package simulator;

import controller.ControllerRobot;
import controller.InteractionHandler;
import model.Area;
import model.Robot;

import java.util.ArrayList;
import java.util.List;

public class Simulator implements SimulatorInterface {

    private List<Robot> robots;
    private List<Area> areas;
    private ControllerRobot controllerRobot;
    private InteractionHandler interactionHandler;

    public Simulator(List<Robot> robots, List<Area> areas) {
        this.robots = robots;
        this.areas = areas;
        this.controllerRobot = new ControllerRobot(robots.get(0), areas.get(0), this);
        this.interactionHandler = new InteractionHandler();
    }

    @Override
    public void simulate(double dt, double time) {
        for (double currentTime = 0; currentTime < time; currentTime += dt) {
            updateRobots(dt);
            checkRobotAreaInteraction();
        }
    }

    private void updateRobots(double dt) {
        for (Robot robot : robots) {
            if (robot.isMoving()) {
                double deltaX = robot.getSpeed() * dt;
                robot.move(deltaX, 0);
            }
        }
    }

    private void checkRobotAreaInteraction() {
        for (Robot robot : robots) {
            for (Area area : areas) {
                if (area.contains(robot.getX(), robot.getY())) {
                    interactionHandler.handleInteraction(robot, area);
                }
            }
        }
    }

    private Robot findRobotByLabel(String label) {
        // Trova un robot nella lista in base all'etichetta specificata
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
