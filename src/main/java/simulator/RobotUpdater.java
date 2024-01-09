package simulator;

import model.Robot;

import java.util.List;

public interface RobotUpdater {
    void updateRobots(List<Robot> robots, double dt);
}
