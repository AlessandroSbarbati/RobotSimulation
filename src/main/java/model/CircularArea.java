package model;

public class CircularArea implements Area {
    private final double centerX;
    private final double centerY;
    private final double radius;

    public CircularArea(double centerX, double centerY, double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Il raggio deve essere positivo.");
        }

        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    @Override
    public boolean contains(double x, double y) {
        double distanceSquared = Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2);
        return distanceSquared <= Math.pow(radius, 2);
    }

    @Override
    public double getMaxX() {
        return centerX + radius;
    }

    @Override
    public double getMinX() {
        return centerX - radius;
    }

    @Override
    public double getMaxY() {
        return centerY + radius;
    }

    @Override
    public double getMinY() {
        return centerY - radius;
    }
}
