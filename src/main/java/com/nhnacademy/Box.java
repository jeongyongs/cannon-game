package com.nhnacademy;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Box implements Regionable, Paintable {

    private Point location;
    private int width;
    private int height;
    private Color color;
    private static int totalCount = 0;
    private Type type;

    public Box(Point location, int width, int height, Color color) {
        this.location = location;
        this.width = width;
        this.height = height;
        this.color = color;
        totalCount++;
    }

    public Box(Point location, int width, int height) {
        this(location, width, height, Color.BLACK);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
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

    protected Color getColor() {
        return color;
    }

    protected void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Rectangle getRegion() {
        return new Rectangle((int) (getLocation().getX() - getWidth() / 2),
                (int) (getLocation().getY() - getHeight() / 2), getWidth(), 2 * getHeight());
    }

    @Override
    public void paint(Graphics graphics) {
        Color previous = graphics.getColor();

        graphics.setColor(getColor());
        graphics.fillRect((int) (getLocation().getX() - getWidth() / 2),
                (int) (getLocation().getY() - getHeight() / 2), getWidth(), getHeight());
        graphics.setColor(previous);
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }
}
