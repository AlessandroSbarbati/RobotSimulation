import static org.junit.jupiter.api.Assertions.*;

import controller.DefaultInteractionHandler;
import model.Area;
import model.CircularArea;
import model.RectangularArea;
import model.Robot;
import org.junit.jupiter.api.Test;

class DefaultInteractionHandlerTest {

    @Test
    void testHandleInteractionWithRectangularArea() {
        DefaultInteractionHandler interactionHandler = new DefaultInteractionHandler();
        Robot robot = new Robot(0,0,0.1);
        RectangularArea rectangularArea = new RectangularArea(0, 0, 10, 10); // Sostituisci con i valori corretti

        interactionHandler.handleInteraction(robot, rectangularArea);

        // Verifica che il robot abbia la condizione corretta aggiunta
        assertTrue(robot.hasCondition("Interacting with RectangularArea"));
        assertFalse(robot.hasCondition("Interacting with CircularArea"));
    }

    @Test
    void testHandleInteractionWithCircularArea() {
        DefaultInteractionHandler interactionHandler = new DefaultInteractionHandler();
        Robot robot = new Robot(0,0,0.1);
        CircularArea circularArea = new CircularArea(0, 0, 5); // Sostituisci con i valori corretti

        interactionHandler.handleInteraction(robot, circularArea);

        // Verifica che il robot abbia la condizione corretta aggiunta
        assertTrue(robot.hasCondition("Interacting with CircularArea"));
        assertFalse(robot.hasCondition("Interacting with RectangularArea"));
    }

    @Test
    void testHandleInteractionWithNonSpecificArea() {
        DefaultInteractionHandler interactionHandler = new DefaultInteractionHandler();
        Robot robot = new Robot(0,0,0.1);
        Area genericArea = new RectangularArea(0, 0, 5, 5); // Sostituisci con i valori corretti

        interactionHandler.handleInteraction(robot, genericArea);

        // Verifica che il robot non abbia alcuna condizione aggiunta
        assertFalse(robot.hasCondition("Interacting with RectangularArea"));
        assertFalse(robot.hasCondition("Interacting with CircularArea"));
    }

}
