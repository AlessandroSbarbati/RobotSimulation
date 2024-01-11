import controller.ShapeDataHandler;
import model.Area;
import model.CircularArea;
import model.RectangularArea;
import model.ShapeData;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ShapeDataHandlerTest {

    @Test
    void createCircularArea_validShapeData_returnsCircularArea() {
        // Arrange
        ShapeData shapeData = new ShapeData("Label", "CIRCLE", new double[]{1.0, 2.0, 3.0});

        // Act
        Area result = ShapeDataHandler.createAreaFromShape(shapeData);

        // Assert
        assertTrue(result instanceof CircularArea);
        CircularArea circularArea = (CircularArea) result;
        assertEquals(1.0, circularArea.getCenterX());
        assertEquals(2.0, circularArea.getCenterY());
        assertEquals(3.0, circularArea.getRadius());
    }

    @Test
    void createRectangularArea_validShapeData_returnsRectangularArea() {
        // Arrange
        ShapeData shapeData = new ShapeData("Label", "RECTANGLE", new double[]{1.0, 2.0, 3.0, 4.0});

        // Act
        Area result = ShapeDataHandler.createAreaFromShape(shapeData);

        // Assert
        assertTrue(result instanceof RectangularArea);
        RectangularArea rectangularArea = (RectangularArea) result;
        assertEquals(1.0, rectangularArea.getTopLeftX());
        assertEquals(2.0, rectangularArea.getTopLeftY());
        assertEquals(3.0, rectangularArea.getWidth());
        assertEquals(4.0, rectangularArea.getHeight());
    }

    @Test
    void createAreaFromShape_invalidShape_throwsIllegalArgumentException() {
        // Arrange
        ShapeData shapeData = new ShapeData("Label", "INVALID_SHAPE", new double[]{});

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> ShapeDataHandler.createAreaFromShape(shapeData));
    }

    @Test
    void createCircularArea_invalidArguments_throwsIllegalArgumentException() {
        // Arrange
        ShapeData shapeData = new ShapeData("Label", "CIRCLE", new double[]{1.0, 2.0});

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> ShapeDataHandler.createAreaFromShape(shapeData));
    }

    @Test
    void createRectangularArea_invalidArguments_throwsIllegalArgumentException() {
        // Arrange
        ShapeData shapeData = new ShapeData("Label", "RECTANGLE", new double[]{1.0, 2.0, 3.0});

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> ShapeDataHandler.createAreaFromShape(shapeData));
    }
}
