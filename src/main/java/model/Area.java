    package model;

    public interface Area {
        boolean contains(double x, double y);
        double getMaxX();
        double getMinX();
        double getMaxY();
        double getMinY();
    }
