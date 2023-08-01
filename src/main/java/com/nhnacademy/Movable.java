package com.nhnacademy;

import java.awt.Point;

public interface Movable extends Runnable {

    public void setMotion(Motion motion);

    public Motion getMotion();

    public void move();

    public void moveTo(Point location);

    public int getDelay();

    public void setDelay(int dT);

    public void start();

    public void stop();

    public void setWorld(MovableWorld world);

    public MovableWorld getWorld();

}
