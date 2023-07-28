package com.nhnacademy;

import java.awt.Color;
import java.awt.Point;

public class Ball {

    private Point location;
    private int radius;
    private Color color;

    public Ball(Point location, int radius, Color color) {
        this.location = location;
        this.radius = radius;
        this.color = color;
    }

    public Ball(Point location, int radius) {
        this(location, radius, Color.BLACK);
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
