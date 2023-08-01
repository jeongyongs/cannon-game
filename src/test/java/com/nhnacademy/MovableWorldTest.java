package com.nhnacademy;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.awt.Color;
import java.awt.Point;

import javax.swing.JFrame;

import org.junit.jupiter.api.Test;

public class MovableWorldTest {

    private static final int BALL_X = 40;
    private static final int BALL_Y = 460;
    private static final int BALL_RADIUS = 20;
    private static final Color BALL_COLOR = Color.RED;
    private static final int BALL_POWER = 30;
    private static final int BALL_ANGLE = -60;

    private static final int TOTAL_FRAME = 50;
    private static final int DELAY = 10;

    private static final int FRAME_X = 1000;
    private static final int FRAME_Y = 500;
    private static final int FRAME_WIDTH = 700;
    private static final int FRAME_HEIGHT = 500;

    public static void main(String[] args) {
        testVisible();
    }

    private static void testVisible() {
        JFrame frame = new JFrame();
        MovableWorld world = new MovableWorld();
        MovableBall ball = new MovableBall(new Point(BALL_X, BALL_Y), BALL_RADIUS, BALL_COLOR);

        ball.setMotion(Motion.createDisplacement(BALL_POWER, BALL_ANGLE));

        world.add(ball);
        world.setMaxMoveCount(TOTAL_FRAME);
        world.setDelay(DELAY);
        world.addEffect(Motion.createPosition(0, 2));

        frame.add(world);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(FRAME_X, FRAME_Y);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);

        world.run();
    }

    @Test
    public void testAddEffect() {
        // given
        MovableWorld movableWorld = new MovableWorld();
        Motion motion = Motion.createPosition(0, 10);

        // when
        movableWorld.addEffect(motion);

        // then
        assertAll("testEffect",
                () -> assertEquals(1, movableWorld.getEffectCount()),
                () -> assertEquals(motion, movableWorld.getEffect(0)));
    }

    @Test
    public void testRemoveEffect() {
        // given
        MovableWorld movableWorld = new MovableWorld();
        Motion motion = Motion.createPosition(0, 10);

        // when
        movableWorld.addEffect(motion);
        movableWorld.removeEffect(motion);

        // then
        assertAll("testEffect",
                () -> assertEquals(0, movableWorld.getEffectCount()));
    }
}
