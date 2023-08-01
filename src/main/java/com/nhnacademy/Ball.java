package com.nhnacademy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Ball implements Regionable, Paintable {

    private Point location;
    private int radius;
    private Color color;
    private static int totalCount = 0;
    private boolean boundVisible = false;

    public Ball(Point location, int radius, Color color) {
        this.location = location;
        this.radius = radius;
        this.color = color;
        totalCount++;
    }

    public Ball(Point location, int radius) {
        this(location, radius, Color.BLACK);
    }

    public int getTotalCount() {
        return totalCount;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    protected int getRadius() {
        return radius;
    }

    protected void setRadius(int radius) {
        this.radius = radius;
    }

    protected Color getColor() {
        return color;
    }

    protected void setColor(Color color) {
        this.color = color;
    }

    protected void setBoundVisible(boolean visible) {
        boundVisible = visible;
    }

    @Override
    public Rectangle getRegion() {
        return new Rectangle((int) (getLocation().getX() - getRadius()), (int) (getLocation().getY() - getRadius()),
                2 * getRadius(), 2 * getRadius());
    }

    @Override
    public void paint(Graphics graphics) {
        Color previous = graphics.getColor();

        graphics.setColor(getColor());
        graphics.fillOval((int) (getLocation().getX() - getRadius()), (int) (getLocation().getY() - getRadius()),
                2 * getRadius(), 2 * getRadius());
        if (boundVisible) {
            graphics.drawRect((int) (getLocation().getX() - getRadius()), (int) (getLocation().getY() - getRadius()),
                    2 * getRadius(), 2 * getRadius());
        }
        graphics.setColor(previous);
    }
}
