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
                    // Gestisci l'interazione tra il robot e l'area
                    // Puoi personalizzare questa logica in base alle tue esigenze
                }
            }
        }
    }
}
