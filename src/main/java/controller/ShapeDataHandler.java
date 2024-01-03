package controller;

import model.Area;
import model.CircularArea;
import model.RectangularArea;
import model.ShapeData;

public class ShapeDataHandler {
    public static Area createAreaFromShape(ShapeData shapeData) {
        String shape = shapeData.shape().toUpperCase();

        switch (shape) {
            case "CIRCLE":
                return createCircularArea(shapeData);
            case "RECTANGLE":
                return createRectangularArea(shapeData);
            // Puoi aggiungere altri tipi di forme se necessario
            default:
                throw new IllegalArgumentException("Forma non supportata: " + shape);
        }
    }

    private static CircularArea createCircularArea(ShapeData shapeData) {
        double[] args = shapeData.args();
        if (args.length != 3) {
            throw new IllegalArgumentException("Numero errato di argomenti per la forma circolare");
        }

        double centerX = args[0];
        double centerY = args[1];
        double radius = args[2];

        return new CircularArea(centerX, centerY, radius);
    }

    private static RectangularArea createRectangularArea(ShapeData shapeData) {
        double[] args = shapeData.args();
        if (args.length != 4) {
            throw new IllegalArgumentException("Numero errato di argomenti per la forma rettangolare");
        }

        double topLeftX = args[0];
        double topLeftY = args[1];
        double width = args[2];
        double height = args[3];

        return new RectangularArea(topLeftX, topLeftY, width, height);
    }
}

