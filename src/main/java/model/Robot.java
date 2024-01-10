package model;

import java.util.HashSet;
import java.util.Set;

/**
 * La classe Robot rappresenta un robot all'interno del sistema. Ogni robot ha una posizione (x, y),
 * una velocità, condizioni correnti, e può trovarsi in uno stato di movimento o fermo.
 */
public class Robot {

    private double x;
    private double y;
    private double speed;
    private boolean moving;
    private final Set<String> conditions;
    private boolean following;
    private String followLabel;

    /**
     * Costruttore per la classe Robot.
     *
     * @param x     La coordinata x iniziale del robot.
     * @param y     La coordinata y iniziale del robot.
     * @param speed La velocità iniziale del robot.
     */
    public Robot(double x, double y, double speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.moving = false;
        this.conditions = new HashSet<>();
        this.following = false;
    }

    /**
     * Restituisce la coordinata x del robot.
     *
     * @return La coordinata x del robot.
     */
    public double getX() {
        return x;
    }

    /**
     * Restituisce la coordinata y del robot.
     *
     * @return La coordinata y del robot.
     */
    public double getY() {
        return y;
    }

    /**
     * Verifica se il robot è in movimento.
     *
     * @return True se il robot è in movimento, altrimenti False.
     */
    public boolean isMoving() {
        return moving;
    }

    /**
     * Restituisce l'insieme delle condizioni correnti del robot.
     *
     * @return Un insieme di stringhe rappresentanti le condizioni del robot.
     */
    public Set<String> getConditions() {
        return conditions;
    }

    /**
     * Sposta il robot di una quantità specificata lungo gli assi x e y.
     *
     * @param deltaX La quantità da spostare lungo l'asse x.
     * @param deltaY La quantità da spostare lungo l'asse y.
     */
    public void move(double deltaX, double deltaY) {
        if (moving) {
            x += deltaX;
            y += deltaY;
            System.out.println("Il robot si è spostato a (" + x + ", " + y + ")");
        } else {
            System.out.println("Impossibile spostarsi, il robot non è in movimento.");
        }
    }

    /**
     * Avvia il movimento del robot.
     */
    public void startMoving() {
        if (!moving) {
            moving = true;
            conditions.add("Moving");
            System.out.println("Il robot ha iniziato a muoversi.");
        } else {
            System.out.println("Il robot è già in movimento.");
        }
    }

    /**
     * Ferma il movimento del robot.
     */
    public void stopMoving() {
        if (moving) {
            moving = false;
            conditions.add("Stopped");
            System.out.println("Il robot si è fermato.");
        } else {
            System.out.println("Il robot è già fermo.");
        }
    }

    /**
     * Aggiunge una nuova condizione al robot.
     *
     * @param newCondition La nuova condizione da aggiungere al robot.
     */
    public void addCondition(String newCondition) {
        conditions.add(newCondition);
        System.out.println("Nuova condizione aggiunta: " + newCondition);
    }

    /**
     * Rimuove una condizione dal robot.
     *
     * @param conditionToRemove La condizione da rimuovere dal robot.
     */
    public void removeCondition(String conditionToRemove) {
        conditions.remove(conditionToRemove);
        System.out.println("Condizione rimossa: " + conditionToRemove);
    }

    /**
     * Verifica se il robot ha una specifica condizione.
     *
     * @param checkCondition La condizione da verificare.
     * @return True se il robot ha la condizione, altrimenti False.
     */
    public boolean hasCondition(String checkCondition) {
        return conditions.contains(checkCondition);
    }

    /**
     * Restituisce la velocità del robot.
     *
     * @return La velocità del robot.
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * Imposta una nuova velocità per il robot.
     *
     * @param newSpeed La nuova velocità da impostare per il robot.
     */
    public void setSpeed(double newSpeed) {
        this.speed = newSpeed;
        System.out.println("La velocità del robot è stata impostata a: " + newSpeed + " m/s");
    }

    /**
     * Verifica se il robot sta seguendo un'altra entità.
     *
     * @return True se il robot sta seguendo, altrimenti False.
     */
    public boolean isFollowing() {
        return following;
    }

    /**
     * Imposta lo stato di seguimento del robot.
     *
     * @param following True se il robot deve iniziare a seguire, altrimenti False.
     */
    public void setFollowing(boolean following) {
        this.following = following;
    }

    /**
     * Restituisce l'etichetta dell'entità che il robot sta seguendo.
     *
     * @return L'etichetta dell'entità seguita dal robot.
     */
    public String getFollowLabel() {
        return followLabel;
    }

    /**
     * Imposta l'etichetta dell'entità da seguire.
     *
     * @param followLabel L'etichetta dell'entità da seguire.
     */
    public void setFollowLabel(String followLabel) {
        this.followLabel = followLabel;
    }
}
