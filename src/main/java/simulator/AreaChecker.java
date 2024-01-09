package simulator;

import controller.InteractionHandler;
import model.Area;
import model.Robot;

import java.util.List;

public interface AreaChecker {
    void checkRobotAreaInteraction(List<Robot> robots, List<Area> area, InteractionHandler interactionHandler);

}
