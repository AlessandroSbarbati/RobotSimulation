package model;

public class CircularArea implements Area{
    private double centerX;
    private double centerY;
    private double radius;

    public CircularArea(double centerX, double centerY, double radius){
        this.centerX=centerX;
        this.centerY=centerY;
        this.radius=radius;
    }
    @Override
    public boolean contains(double x, double y) {
        double distanceSquared = Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2);
        return distanceSquared <= Math.pow(radius, 2);
    }
    public void reactToRobot(Robot robot) {
        System.out.println("Il robot è entrato nell'area circolare.");

        // Esempio di azioni più complesse:
        if (robot.hasCondition("Moving")) {
            // Se il robot è in movimento, rallenta quando entra nell'area circolare
            System.out.println("Il robot rallenta dentro l'area circolare.");
            robot.setSpeed(robot.getSpeed() / 2);
        } else {
            // Altrimenti, inizia a muoversi se non è già in movimento
            System.out.println("Il robot inizia a muoversi dentro l'area circolare.");
            robot.startMoving();
        }

        // Aggiungi una condizione specifica per l'area circolare
        robot.addCondition("InCircularArea");
    }
}
