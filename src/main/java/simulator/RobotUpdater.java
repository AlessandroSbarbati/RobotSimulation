package simulator;

import model.Robot;

import java.util.List;

/**
 * L'interfaccia RobotUpdater definisce il contratto per l'aggiornamento dei robot nella simulazione.
 */
public interface RobotUpdater {

    /**
     * Aggiorna lo stato dei robot nella simulazione.
     *
     * @param robots La lista dei robot da aggiornare.
     * @param dt     Il passo temporale della simulazione.
     */
    void updateRobots(List<Robot> robots, double dt);
}
