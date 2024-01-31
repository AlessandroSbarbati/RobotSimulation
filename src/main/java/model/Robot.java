package model;

import java.util.ArrayList;

/**
 * La classe Robot rappresenta un robot all'interno del sistema. Ogni robot ha una posizione (x, y),
 * una velocità, condizioni correnti, e può trovarsi in uno stato di movimento o fermo.
 */
public class Robot implements RobotInterface{
    private final ArrayList<String> condizioni;
    private CoordinateRobot coordinate;

    public Robot(CoordinateRobot coordinate) {
        this.condizioni = new ArrayList<>();
        this.coordinate=coordinate;
    }

    public Robot(double x, double y, double velocita){
        this.condizioni= new ArrayList<>();
        this.coordinate=new CoordinateRobot(x,y,velocita);
    }

    /**
     * Aggiunge una nuova condizione al robot.
     *
     * @param newCondizione La nuova condizione da aggiungere al robot.
     */
    public void addCondition(String newCondizione) {
        condizioni.add(newCondizione);
    }

    /**
     * Rimuove una condizione dal robot.
     *
     * @param condizioneRimossa La condizione da rimuovere dal robot.
     */
    public boolean removeCondition(String condizioneRimossa) {
        return condizioni.remove(condizioneRimossa);
    }

    /**
     * Verifica se il robot ha una specifica condizione.
     *
     * @param checkCondizione La condizione da verificare.
     * @return True se il robot ha la condizione, altrimenti False.
     */
    public boolean hasCondition(String checkCondizione) {
        return condizioni.contains(checkCondizione);
    }

    public CoordinateRobot getCoordinate() {
        return coordinate;
    }

    public CoordinateRobot setCoordinate(double x, double y, double velocita) {
        coordinate = new CoordinateRobot(x, y, velocita);
        return this.coordinate;
    }
}
