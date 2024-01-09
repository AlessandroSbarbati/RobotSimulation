package model;

public class RectangularArea implements Area{
    private double topLeftX;
    private double topLeftY;
    private double width;
    private double height;

    public RectangularArea(double topLeftX, double topLeftY, double width, double height){
        if (width<=0 || height<=0){
            throw new IllegalArgumentException("Larghezza e altezza devono essere entrambe positive.");
        }

        this.topLeftX=topLeftX;
        this.topLeftY=topLeftY;
        this.width=width;
        this.height=height;
    }
    @Override
    public boolean contains(double x, double y) {
        return x >= topLeftX && x <= topLeftX + width && y >= topLeftY && y <= topLeftY + height;
    }
}
