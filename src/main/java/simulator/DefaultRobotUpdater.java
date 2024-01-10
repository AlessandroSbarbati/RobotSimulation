package simulator;

import model.Robot;

import java.util.List;

/**
 * La classe DefaultRobotUpdater implementa l'interfaccia RobotUpdater e fornisce un'implementazione di base
 * per l'aggiornamento dei robot nella simulazione.
 */
public class DefaultRobotUpdater implements RobotUpdater {

    /**
     * Aggiorna lo stato dei robot nella simulazione, spostandoli in base alla loro velocità.
     *
     * @param robots La lista dei robot da aggiornare.
     * @param dt     Il passo temporale della simulazione.
     */
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
