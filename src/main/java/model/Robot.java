package model;

import java.security.cert.CertPathValidatorException;

public class Robot {
    private double x;
    private double y;
    private double speed;
    private boolean moving;
    private String condition;

    public Robot(double x, double y, double speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.moving = false;
        this.condition = "";
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

    public String getCondition() {
        return condition;
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
            condition = "Moving";
            System.out.println("Il robot è iniziato a muoversi.");
        } else {
            System.out.println("Il robot è già in movimento.");
        }
    }

    public void stopMoving() {
        // Implementa la logica per fermare il movimento del robot
        if (moving) {
            moving = false;
            condition = "Stopped";
            System.out.println("Il robot si è fermato.");
        } else {
            System.out.println("Il robot è già fermo.");
        }
    }

    public void addCondition(String newCondition) {
        // Implementa la logica per aggiungere una nuova condizione al robot
        condition += (condition.isEmpty() ? "" : ", ") + newCondition;
        System.out.println("Nuova condizione aggiunta: " + newCondition);
    }

    public void removeCondition(String conditionToRemove) {
        // Implementa la logica per rimuovere una condizione dal robot
        condition = condition.replace(conditionToRemove + ", ", "");
        condition = condition.replace(", " + conditionToRemove, "");
        condition = condition.replace(conditionToRemove, "");
        System.out.println("Condizione rimossa: " + conditionToRemove);
    }

    public boolean hasCondition(String checkCondition) {
        // Implementa la logica per verificare se il robot ha una determinata condizione
        return condition.contains(checkCondition);
    }

    public double getSpeed() {
        return speed;
    }

    public String getConditions() {
        return condition;
    }
}
