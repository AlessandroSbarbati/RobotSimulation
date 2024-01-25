package controller;

import model.*;
import utils.FollowMeParser;
import utils.FollowMeParserException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * La classe ShapeDataHandler gestisce la creazione di aree in base ai dati della forma specificati.
 */
public class ShapeDataHandler<R extends RobotInterface> {
    private final FollowMeParser parser;

    public ShapeDataHandler(FollowMeParser parser){
        this.parser=parser;
    }
    public ArrayList<Area> parserSpazio(String path) {
        try {
            ArrayList<Area> areeList = new ArrayList<>();
            File configurazioneSpazio = new File(path);
            List<ShapeData> shapes = parser.parseEnvironment(configurazioneSpazio);
            for (ShapeData shape : shapes) {
                if (shape.shape().equals("RECTANGLE"))
                    areeList.add(new RectangularArea(shape));
                if (shape.shape().equals("CIRCLE"))
                    areeList.add(new CircularArea(shape));
            }
            return areeList;
        } catch (FollowMeParserException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public InfiniteSurface<Robot, Area> generaSpazio(String configurazioneSpazioPath, HashMap<Robot, Coordinate> robotCoordinatesHashMap) {
        return new InfiniteSurface<>(parserSpazio(configurazioneSpazioPath), robotCoordinatesHashMap);
    }
}

