package com.nhnacademy;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.function.Predicate;

public class BoundedBall extends MovableBall implements Bouncable {

    private boolean resistance = false;

    public BoundedBall(Point location, int radius, Color color) {
        super(location, radius, color);
    }

    public BoundedBall(Point location, int radius) {
        super(location, radius);
    }

    public void setResistance(boolean resistance) {
        this.resistance = resistance;
    }

    public boolean getResistance() {
        return resistance;
    }

    @Override
    public boolean isOutOfBounds() {
        Rectangle intersection = getRegion().intersection(getWorld().getBounds());

        return intersection.getWidth() != getRegion().getWidth()
                || intersection.getHeight() != getRegion().getHeight();
    }

    @Override
    public void bounce(Predicate<Object> predicate, Runnable runnable) {
        if (predicate.test(null)) {
            runnable.run();
        }
    }

    @Override
    public void move() {
        super.move();

        if (isOutOfBounds()) {
            bounce(ignore -> getLocation().getX() - getRadius() < getWorld().getBounds().getMinX(), // 좌측
                    () -> {
                        getMotion().setDX(Math.abs(getMotion().getDX()));
                        setLocation(new Point((int) (getWorld().getBounds().getMinX() + getRegion().getWidth() / 2),
                                (int) getRegion().getCenterY()));
                        getMotion().add(Motion.createPosition(-1, 0));
                    });

            bounce(ignore -> getLocation().getX() + getRadius() > getWorld().getBounds().getMaxX(), // 우측
                    () -> {
                        getMotion().setDX(-Math.abs(getMotion().getDX()));
                        setLocation(new Point((int) (getWorld().getBounds().getMaxX() - getRegion().getWidth() / 2),
                                (int) getRegion().getCenterY()));
                        getMotion().add(Motion.createPosition(1, 0));
                    });

            bounce(ignore -> getLocation().getY() - getRadius() < getWorld().getBounds().getMinY(), // 상단
                    () -> {
                        getMotion().setDY(Math.abs(getMotion().getDY()));
                        setLocation(new Point((int) getRegion().getCenterX(),
                                (int) (getWorld().getBounds().getMinY() + getRegion().getHeight() / 2)));
                        getMotion().add(Motion.createPosition(0, -1));
                    });

            bounce(ignore -> getLocation().getY() + getRadius() > getWorld().getBounds().getMaxY(), // 하단
                    () -> {
                        getMotion().setDY(-Math.abs(getMotion().getDY()));
                        setLocation(new Point((int) getRegion().getCenterX(),
                                (int) (getWorld().getBounds().getMaxY() - getRegion().getHeight() / 2)));
                        getMotion().add(Motion.createPosition(0, 1));

                        if (resistance && getMotion().getDX() != 0) { // 마 찰
                            getMotion().setDX(getMotion().getDX()
                                    - (getMotion().getDX() / Math.abs(getMotion().getDX())));
                        }
                    });
        }
    }
}
