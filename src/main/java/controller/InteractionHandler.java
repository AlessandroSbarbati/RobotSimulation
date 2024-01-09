package controller;

import model.Area;
import model.CircularArea;
import model.RectangularArea;
import model.Robot;

public class InteractionHandler implements InterfaceInteractionHandler{
    public void handleInteraction(Robot robot, Area area) {
        if (area instanceof RectangularArea) {
            robot.addCondition("Interacting with RectangularArea");
        } else if (area instanceof CircularArea) {
            robot.addCondition("Interacting with CircularArea");
        }
    }


}
