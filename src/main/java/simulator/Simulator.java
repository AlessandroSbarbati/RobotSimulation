package simulator;

import controller.ControllerRobot;
import model.Robot;
import model.Area;
import utils.RobotCommand;

import java.util.List;

public class Simulator implements SimulatorInterface {

    private List<Robot> robots;
    private List<Area> areas;
    private ControllerRobot controllerRobot;

    public Simulator(List<Robot> robots, List<Area> areas) {
        this.robots = robots;
        this.areas = areas;
        this.controllerRobot=new ControllerRobot(robots.get(0),areas.get(0),this);
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
                    handleInteraction(robot, area);
                }
            }
        }
    }

    private void handleInteraction(Robot robot, Area area) {
        // Logica specifica di come il robot deve interagire con l'area
        // Ad esempio, puoi chiamare metodi sul robot o sull'area per gestire l'interazione.
        System.out.println("Il robot sta interagendo con un'area.");
        if (robot.isFollowing()) {
            Robot followedRobot = findRobotByLabel(robot.getFollowLabel());
            if (followedRobot != null) {
                // Aggiorna la posizione del robot in base al robot seguito
                robot.move(followedRobot.getX() - robot.getX(), followedRobot.getY() - robot.getY());
            }
        }
    }

    public Robot findRobotByLabel(String label) {
        // Trova un robot nella lista in base all'etichetta specificata
        return robots.stream()
                .filter(robot -> robot.getCondition().contains(label))
                .findFirst()
                .orElse(null);
    }
}
