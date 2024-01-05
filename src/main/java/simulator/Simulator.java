package simulator;

import model.Robot;
import model.Area;

import java.util.List;

public class Simulator implements SimulatorInterface {

    private List<Robot> robots;
    private List<Area> areas;

    public Simulator(List<Robot> robots, List<Area> areas) {
        this.robots = robots;
        this.areas = areas;
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

        System.out.println("Il robot sta interagendo con l'area: " + area);
        robot.addCondition("Interacting"); // Esempio di aggiunta di una condizione
        // Puoi definire altre azioni specifiche qui...
        area.reactToRobot(robot);
        robot.removeCondition("Interacting"); // Esempio di rimozione di una condizione
    }
}
