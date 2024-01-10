package controller;

import model.Area;
import model.Robot;
/**
 * L'interfaccia InteractionHandler definisce il contratto per gestire l'interazione tra un robot e un'area.
 */
public interface InteractionHandler {
    /**
     * Gestisce l'interazione tra il robot e l'area specificata.
     *
     * @param robot Il robot coinvolto nell'interazione.
     * @param area  L'area con cui il robot interagisce.
     */
    void handleInteraction(Robot robot, Area area);
}

