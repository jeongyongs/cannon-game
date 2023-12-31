package com.nhnacademy;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

public class CollisionBall extends BoundedBall {

    public CollisionBall(Point location, int radius, Color color) {
        super(location, radius, color);
    }

    public CollisionBall(Point location, int radius) {
        super(location, radius);
    }

    public void calcCollision() {
        getWorld().getItems()
                .stream()
                .filter(item -> item != this && item.getRegion().intersects(getRegion()))
                .forEach(this::bounceFromObject);
    }

    private void bounceFromObject(Regionable item) {
        Rectangle intersection = item.getRegion().intersection(getRegion());

        if (intersection.getWidth() <= intersection.getHeight()) {
            if (intersection.getCenterX() < getRegion().getCenterX()) { // 좌 충돌
                getMotion().setDX(Math.abs(getMotion().getDX()));
                setLocation(new Point((int) (getRegion().getCenterX() + intersection.getWidth()),
                        (int) getRegion().getCenterY()));
                getMotion().add(Motion.createPosition(-1, 0));
            }
            if (intersection.getCenterX() > getRegion().getCenterX()) { // 우 충돌
                getMotion().setDX(-Math.abs(getMotion().getDX()));
                setLocation(new Point((int) (getRegion().getCenterX() - intersection.getWidth()),
                        (int) getRegion().getCenterY()));
                getMotion().add(Motion.createPosition(1, 0));
            }
        }
        if (intersection.getWidth() >= intersection.getHeight()) {
            if (intersection.getCenterY() < getRegion().getCenterY()) { // 상 충돌
                getMotion().setDY(Math.abs(getMotion().getDY()));
                setLocation(new Point((int) getRegion().getCenterX(),
                        (int) (getRegion().getCenterY() + intersection.getHeight())));
                getMotion().add(Motion.createPosition(0, -1));
            }
            if (intersection.getCenterY() > getRegion().getCenterY()) { // 하 충돌
                getMotion().setDY(-Math.abs(getMotion().getDY()));
                setLocation(new Point((int) getRegion().getCenterX(),
                        (int) (getRegion().getCenterY() - intersection.getHeight())));
                getMotion().add(Motion.createPosition(0, 1));

                if (getResistance() && getMotion().getDX() != 0) { // 마 찰
                    getMotion().setDX(getMotion().getDX()
                            - (getMotion().getDX() / Math.abs(getMotion().getDX())));
                }
            }
        }
    }

    @Override
    public void move() {
        super.move();
        calcCollision();
    }
}

// 위치, 질량, 속도, 힘
