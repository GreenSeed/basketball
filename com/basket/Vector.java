package com.basket;

public class Vector {
    public double x;
    public double y;

    public Vector(double x, double y) {
        set(x, y);
    }

    public void set(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector multiply(double k) {

        return new Vector(x * k, y * k);
    }

    public void addVector(Vector k) {
        this.x += k.x;
        this.y += k.y;
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }
}