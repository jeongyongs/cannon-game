package com.nhnacademy;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class MovableWorld extends World {

    private int maxMoveCount;
    private int delay;
    private List<Motion> effects;

    public MovableWorld() {
        super();
        maxMoveCount = 1;
        delay = 10;
        effects = new ArrayList<>();
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
                .forEach(item -> {
                    effects.stream().forEach(item.getMotion()::add); // effect 적용
                    item.move(); // 이 동
                });
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
}
