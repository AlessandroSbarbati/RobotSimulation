package simulator;

import controller.DefaultInteractionHandler;
import model.Area;
import model.Robot;

import java.util.List;

/**
 * L'interfaccia AreaChecker fornisce un metodo per verificare l'interazione tra robot e aree specificate.
 */
public interface AreaChecker {

    /**
     * Verifica l'interazione tra i robot e le aree specificate, utilizzando l'InteractionHandler fornito.
     *
     * @param robots              Una lista di robot da verificare.
     * @param areas               Una lista di aree con cui verificare l'interazione.
     * @param interactionHandler  Il gestore di interazione da utilizzare per gestire l'interazione.
     */
    void checkRobotAreaInteraction(List<Robot> robots, List<Area> areas, DefaultInteractionHandler interactionHandler);

}
