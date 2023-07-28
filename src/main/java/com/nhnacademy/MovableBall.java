package com.nhnacademy;

import java.awt.Color;
import java.awt.Point;

public class MovableBall extends Ball implements Movable {

    private Motion motion = Motion.createPosition(0, 0);

    public MovableBall(Point location, int radius, Color color) {
        super(location, radius, color);
    }

    public MovableBall(Point location, int radius) {
        super(location, radius);
    }

    @Override
    public void setMotion(Motion motion) {
        this.motion = motion;
    }

    @Override
    public Motion getMotion() {
        return motion;
    }

    @Override
    public void move() {
        getLocation().translate(getMotion().getDX(), getMotion().getDY());
    }

    @Override
    public void moveTo(Point location) {
        setLocation(location);
    }
}
