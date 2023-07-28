package com.nhnacademy;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class BoundedBall extends MovableBall implements Bouncable {

    private Rectangle worldBounds = new Rectangle();

    public BoundedBall(Point location, int radius, Color color) {
        super(location, radius, color);
    }

    public BoundedBall(Point location, int radius) {
        super(location, radius);
    }

    @Override
    public Rectangle getBounds() {
        return worldBounds;
    }

    @Override
    public void setBounds(Rectangle worldBounds) {
        this.worldBounds = worldBounds;
    }

    @Override
    public boolean isOutOfBounds() {
        Rectangle intersection = getRegion().intersection(getBounds());

        return intersection.getWidth() != getRegion().getWidth()
                || intersection.getHeight() != getRegion().getHeight();
    }

    @Override
    public void bounce() {
        if (getLocation().getX() - getRadius() < getBounds().getMinX()) { // 좌측
            getMotion().setDX(Math.abs(getMotion().getDX()));
        }
        if (getLocation().getX() + getRadius() > getBounds().getMaxX()) { // 우측
            getMotion().setDX(-Math.abs(getMotion().getDX()));
        }
        if (getLocation().getY() - getRadius() < getBounds().getMinY()) { // 상단
            getMotion().setDY(Math.abs(getMotion().getDY()));
        }
        if (getLocation().getY() + getRadius() > getBounds().getMaxY()) { // 하단
            getMotion().setDY(-Math.abs(getMotion().getDY()));
        }
    }

    @Override
    public void move() {
        super.move();

        if (isOutOfBounds()) {
            bounce();
        }
    }
}
