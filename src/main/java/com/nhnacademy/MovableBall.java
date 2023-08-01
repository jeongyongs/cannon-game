package com.nhnacademy;

import java.awt.Color;
import java.awt.Point;

public class MovableBall extends Ball implements Movable {

    private Motion motion = Motion.createPosition(0, 0);
    private int delay = 10;
    private Thread thread = new Thread(this, this.getClass().getSimpleName() + getTotalCount());
    private MovableWorld world;

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
    public void moveTo(Point location) {
        setLocation(location);
    }

    @Override
    public int getDelay() {
        return delay;
    }

    @Override
    public void setDelay(int delay) {
        this.delay = delay;
    }

    @Override
    public void start() {
        thread.start();
    }

    @Override
    public void stop() {
        thread.interrupt();
    }

    @Override
    public void move() {
        applyEffect();
        getLocation().translate(getMotion().getDX(), getMotion().getDY());
    }

    private void applyEffect() {
        world.getEffects() // effects 적용
                .stream()
                .forEach(effect -> getMotion().add(effect));
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            move();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ignore) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void setWorld(MovableWorld world) {
        this.world = world;
    }

    @Override
    public MovableWorld getWorld() {
        return world;
    }
}
