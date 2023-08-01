package com.nhnacademy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MovableWorld extends World implements Runnable {

    private int maxRefreshCount;
    private int delay;
    private List<Motion> effects;
    private Thread thread = new Thread(this, "World");

    public MovableWorld() {
        super();
        maxRefreshCount = 1;
        delay = 10;
        effects = new ArrayList<>();
    }

    public int getMaxRefreshCount() {
        return maxRefreshCount;
    }

    public void setMaxRefreshCount(int maxRefreshCount) {
        this.maxRefreshCount = maxRefreshCount;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void refresh() {
        repaint(); // 화면 갱신

        try { // 화면 갱신 딜레이
            Thread.sleep(delay);
        } catch (InterruptedException ignore) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void run() {
        getItems().stream() // item thread 시작
                .filter(item -> item instanceof Movable)
                .map(item -> (Movable) item)
                .forEach(item -> {
                    item.setDelay(delay);
                    item.start();
                });

        IntStream.range(0, maxRefreshCount) // 화면 갱신
                .forEach(i -> refresh());

        getItems().stream() // item thread 중지
                .filter(item -> item instanceof Movable)
                .map(item -> (Movable) item)
                .forEach(item -> item.stop());
    }

    public void addEffect(Motion effect) {
        effects.add(effect);
    }

    public int getEffectCount() {
        return effects.size();
    }

    public Motion getEffect(int index) {
        return effects.get(index);
    }

    public void removeEffect(Motion effect) {
        effects.remove(effect);
    }

    public void removeEffect(int index) {
        effects.remove(index);
    }

    public void start() {
        thread.start();
    }

    public List<Motion> getEffects() {
        return effects;
    }
}
