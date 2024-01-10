package simulator;

import controller.DefaultInteractionHandler;
import model.Area;
import model.Robot;

import java.util.List;
/**
 * La classe DefaultAreaChecker implementa l'interfaccia AreaChecker per verificare l'interazione tra robot e aree.
 */
public class DefaultAreaChecker implements AreaChecker {
    @Override
    public void checkRobotAreaInteraction(List<Robot> robots, List<Area> areas, DefaultInteractionHandler interactionHandler) {
        for (Robot robot : robots) {
            for (Area area : areas) {
                if (area.contains(robot.getX(), robot.getY())) {
                    interactionHandler.handleInteraction(robot, area);
                }
            }
        }
    }

}
