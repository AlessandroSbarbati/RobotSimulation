package simulator;

import model.Robot;

import java.util.List;

public class DefaultRobotUpdater implements RobotUpdater {
    @Override
    public void updateRobots(List<Robot> robots, double dt) {
        for (Robot robot : robots) {
            if (robot.isMoving()) {
                double deltaX = robot.getSpeed() * dt;
                robot.move(deltaX, 0);
            }
        }
    }
}
