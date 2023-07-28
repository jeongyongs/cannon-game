package com.nhnacademy;

import java.util.stream.IntStream;

public class MovableWorld extends World {

    private int maxMoveCount = 1;
    private int delay = 10;

    public MovableWorld() {
        super();
    }

    public int getMaxMoveCount() {
        return maxMoveCount;
    }

    public void setMaxMoveCount(int maxMoveCount) {
        this.maxMoveCount = maxMoveCount;
    }

    public int getDelay() {
        return delay;
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void move() { // 화면 갱신 (요소 이동)
        getItems().stream()
                .filter(item -> item instanceof Movable)
                .map(item -> (Movable) item)
                .forEach(item -> item.move());
        repaint(); // 모든 요소 이동 후 리페인트

        try {
            Thread.sleep(delay);
        } catch (InterruptedException ignore) {
            Thread.currentThread().interrupt();
        }
    }

    public void run() { // 지정 횟수 만큼 화면 갱신
        IntStream.range(0, maxMoveCount)
                .forEach(i -> move());
    }
}
