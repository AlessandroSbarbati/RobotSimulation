package model;

import java.util.ArrayList;

/**
 * La classe Robot rappresenta un robot all'interno del sistema. Ogni robot ha una posizione (x, y),
 * una velocità, condizioni correnti, e può trovarsi in uno stato di movimento o fermo.
 */
public class Robot implements RobotInterface{
    private final ArrayList<String> condizioni;
    private CoordinateRobot coordinate;

    public Robot() {
        this.condizioni = new ArrayList<>();
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
    public void removeCondition(String condizioneRimossa) {
        condizioni.remove(condizioneRimossa);
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

    public void setCoordinate(double x, double y, double velocita) {
        coordinate = new CoordinateRobot(x, y, velocita);
    }
}
