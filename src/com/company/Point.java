package com.company;

public class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != this.getClass())
            return false;

        return this.x == ((Point) obj).x && this.y == ((Point) obj).y;
    }
}
