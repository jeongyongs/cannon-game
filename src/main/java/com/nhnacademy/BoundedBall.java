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
                    () -> getMotion().setDX(Math.abs(getMotion().getDX())));

            bounce(ignore -> getLocation().getX() + getRadius() > getWorld().getBounds().getMaxX(), // 우측
                    () -> getMotion().setDX(-Math.abs(getMotion().getDX())));

            bounce(ignore -> getLocation().getY() - getRadius() < getWorld().getBounds().getMinY(), // 상단
                    () -> getMotion().setDY(Math.abs(getMotion().getDY())));

            bounce(ignore -> getLocation().getY() + getRadius() > getWorld().getBounds().getMaxY(), // 하단
                    () -> {
                        getMotion().setDY(-Math.abs(getMotion().getDY()));

                        if (Math.abs(getMotion().getDY()) < 3) { // 이탈 방지
                            setLocation(new Point((int) getRegion().getCenterX(),
                                    (int) (getWorld().getBounds().getMaxY() - getRegion().getHeight() / 2)));
                            getMotion().setDY(-Math.abs(getMotion().getDY()));
                        }
                        if (resistance && getMotion().getDX() != 0) { // 마찰 감소
                            getMotion().setDX(getMotion().getDX()
                                    - (getMotion().getDX() / Math.abs(getMotion().getDX())));
                        }
                    });
        }
    }
}
