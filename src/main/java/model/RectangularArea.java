package model;

public class RectangularArea implements Area{
    private double topLeftX;
    private double topLeftY;
    private double width;
    private double height;

    public RectangularArea(double topLeftX, double topLeftY, double width, double height){
        this.topLeftX=topLeftX;
        this.topLeftY=topLeftY;
        this.width=width;
        this.height=height;
    }
    @Override
    public boolean contains(double x, double y) {
        return x >= topLeftX && x <= topLeftX + width && y >= topLeftY && y <= topLeftY + height;
    }
    public void reactToRobot(Robot robot) {
        System.out.println("Il robot è entrato nell'area rettangolare.");

        // Esempio di azioni più complesse:
        if (robot.hasCondition("Moving")) {
            // Se il robot è in movimento, lo ferma quando entra nell'area rettangolare
            System.out.println("Il robot si ferma dentro l'area rettangolare.");
            robot.stopMoving();
        } else {
            // Altrimenti, inizia a muoversi se non è già in movimento
            System.out.println("Il robot inizia a muoversi dentro l'area rettangolare.");
            robot.startMoving();
        }

        // Aggiungi una condizione specifica per l'area rettangolare
        robot.addCondition("InRectangularArea");
    }}
