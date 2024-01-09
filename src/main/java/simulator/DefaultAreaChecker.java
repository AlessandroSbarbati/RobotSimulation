package simulator;

import controller.InteractionHandler;
import model.Area;
import model.Robot;

import java.util.List;

public class DefaultAreaChecker implements AreaChecker {
    @Override
    public void checkRobotAreaInteraction(List<Robot> robots, List<Area> areas, InteractionHandler interactionHandler) {
        for (Robot robot : robots) {
            for (Area area : areas) {
                if (area.contains(robot.getX(), robot.getY())) {
                    interactionHandler.handleInteraction(robot, area);
                }
            }
        }
    }

}
