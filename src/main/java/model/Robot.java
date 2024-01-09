package model;

import java.util.HashSet;
import java.util.Set;

public class Robot {
    private double x;
    private double y;
    private double speed;
    protected boolean moving;
    private Set<String> conditions;
    private boolean following;
    private String followLabel;

    public Robot(double x, double y, double speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.moving = false;
        this.conditions = new HashSet<>();
        this.following = false;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean isMoving() {
        return moving;
    }

    public Set<String> getConditions() {
        return conditions;
    }

    public void move(double deltaX, double deltaY) {
        if (moving) {
            // Implementa la logica per spostare il robot
            x += deltaX;
            y += deltaY;
            System.out.println("Il robot si è spostato a (" + x + ", " + y + ")");
        } else {
            System.out.println("Impossibile spostarsi, il robot non è in movimento.");
        }
    }

    public void startMoving() {
        // Implementa la logica per far iniziare il movimento del robot
        if (!moving) {
            moving = true;
            conditions.add("Moving");
            System.out.println("Il robot ha iniziato a muoversi.");
        } else {
            System.out.println("Il robot è già in movimento.");
        }
    }

    public void stopMoving() {
        // Implementa la logica per fermare il movimento del robot
        if (moving) {
            moving = false;
            conditions.add("Stopped");
            System.out.println("Il robot si è fermato.");
        } else {
            System.out.println("Il robot è già fermo.");
        }
    }

    public void addCondition(String newCondition) {
        // Implementa la logica per aggiungere una nuova condizione al robot
        conditions.add(newCondition);
        System.out.println("Nuova condizione aggiunta: " + newCondition);
    }

    public void removeCondition(String conditionToRemove) {
        // Implementa la logica per rimuovere una condizione dal robot
        conditions.remove(conditionToRemove);
        System.out.println("Condizione rimossa: " + conditionToRemove);
    }

    public boolean hasCondition(String checkCondition) {
        // Implementa la logica per verificare se il robot ha una determinata condizione
        return conditions.contains(checkCondition);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double newSpeed) {
        // Imposta la nuova velocità del robot
        this.speed = newSpeed;
        System.out.println("La velocità del robot è stata impostata a: " + newSpeed + " m/s");
    }

    public boolean isFollowing() {
        return following;
    }

    public void setFollowing(boolean following) {
        this.following = following;
    }

    public String getFollowLabel() {
        return followLabel;
    }

    public void setFollowLabel(String followLabel) {
        this.followLabel = followLabel;
    }
}
