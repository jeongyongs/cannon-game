package com.nhnacademy;

import java.awt.Color;
import java.awt.Point;
import java.util.Random;
import java.util.stream.IntStream;

import javax.swing.JFrame;

public class BoundedWorldTest {

    private static final Random random = new Random();

    private static final int FRAME_X = 800;
    private static final int FRAME_Y = 500;
    private static final int FRAME_WIDTH = 1000;
    private static final int FRAME_HEIGHT = 500;

    private static final int TOTAL_FRAME = Integer.MAX_VALUE;
    private static final int DELAY = 10;

    private static final int MAX_BALL_X = 100;
    private static final int MIN_BALL_X = 99;
    private static final int MAX_BALL_Y = 360;
    private static final int MIN_BALL_Y = 359;
    private static final int MAX_BALL_ANGLE = 300;
    private static final int MIN_BALL_ANGLE = 290;
    private static final int MAX_BALL_POWER = 40;
    private static final int MIN_BALL_POWER = 39;
    private static final int MAX_BALL_RADIUS = 50;
    private static final int MIN_BALL_RADIUS = 30;
    private static final int BALL_COUNT = 1;

    public static void main(String[] args) {
        testVisible();
    }

    private static void testVisible() {
        JFrame frame = new JFrame();
        BoundedWorld world = new BoundedWorld();

        IntStream.range(0, BALL_COUNT)
                .forEach(i -> {
                    final int BALL_RADIUS = MIN_BALL_RADIUS + random.nextInt(MAX_BALL_RADIUS - MIN_BALL_RADIUS);
                    final int BALL_X = MIN_BALL_X + random.nextInt(MAX_BALL_X - MIN_BALL_X);
                    final int BALL_Y = MIN_BALL_Y + random.nextInt(MAX_BALL_Y - MIN_BALL_Y);
                    final Color BALL_COLOR = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
                    final int BALL_POWER = MIN_BALL_POWER + random.nextInt(MAX_BALL_POWER - MIN_BALL_POWER);
                    final int BALL_ANGLE = MIN_BALL_ANGLE + random.nextInt(MAX_BALL_ANGLE - MIN_BALL_ANGLE);

                    MovableBall ball = new MovableBall(new Point(BALL_X, BALL_Y), BALL_RADIUS, BALL_COLOR);

                    ball.setMotion(Motion.createDisplacement(BALL_POWER, BALL_ANGLE));
                    world.add(ball);
                });

        world.setMaxMoveCount(TOTAL_FRAME);
        world.setDelay(DELAY);
        world.addEffect(Motion.createPosition(0, 2)); // 중 력

        frame.add(world);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(FRAME_X, FRAME_Y);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);

        world.run();
    }
}
