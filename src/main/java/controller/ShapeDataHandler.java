package controller;

import model.Area;
import model.CircularArea;
import model.RectangularArea;
import model.ShapeData;
/**
 * La classe ShapeDataHandler gestisce la creazione di aree in base ai dati della forma specificati.
 */
public class ShapeDataHandler {
    /**
     * Crea un'area in base ai dati della forma specificati.
     *
     * @param shapeData I dati della forma.
     * @return Un'istanza di Area creata in base ai dati della forma.
     * @throws IllegalArgumentException Se la forma specificata non è supportata o se il numero di argomenti è errato.
     */
    public static Area createAreaFromShape(ShapeData shapeData) {
        String shape = shapeData.shape().toUpperCase();

        return switch (shape) {
            case "CIRCLE" -> createCircularArea(shapeData);
            case "RECTANGLE" -> createRectangularArea(shapeData);
            // Puoi aggiungere altri tipi di forme se necessario
            default -> throw new IllegalArgumentException("Forma non supportata: " + shape);
        };
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

