package controller;

import model.Area;
import model.CircularArea;
import model.RectangularArea;
import model.Robot;
/**
 * DefaultInteractionHandler è un gestore di interazioni predefinito che implementa l'interfaccia InteractionHandler.
 * Gestisce l'interazione tra un robot e un'area specificata, aggiungendo le condizioni corrispondenti al robot.
 */
public class DefaultInteractionHandler implements InteractionHandler {
    // Costanti per le condizioni di interazione
    private static final String INTERACTING_WITH_RECTANGULAR = "Interacting with RectangularArea";
    private static final String INTERACTING_WITH_CIRCULAR = "Interacting with CircularArea";
    /**
     * Gestisce l'interazione tra il robot e l'area specificata, aggiungendo le condizioni corrispondenti al robot.
     *
     * @param robot Il robot coinvolto nell'interazione.
     * @param area  L'area con cui il robot interagisce.
     */
    @Override
    public void handleInteraction(Robot robot, Area area) {
        if (area instanceof RectangularArea) {
            robot.addCondition(INTERACTING_WITH_RECTANGULAR);
        } else if (area instanceof CircularArea) {
            robot.addCondition(INTERACTING_WITH_CIRCULAR);
        }
    }
}
