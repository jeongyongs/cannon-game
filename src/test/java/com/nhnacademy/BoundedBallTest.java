package com.nhnacademy;

import java.awt.Color;
import java.awt.Point;
import javax.swing.JFrame;

public class BoundedBallTest {

    private static final int BALL_X = 100;
    private static final int BALL_Y = 100;
    private static final int BALL_RADIUS = 20;
    private static final Color BALL_COLOR = Color.RED;
    private static final int BALL_POWER = 10;
    private static final int BALL_ANGLE = 45;

    private static final int TOTAL_FRAME = Integer.MAX_VALUE;
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
        BoundedBall ball = new BoundedBall(new Point(BALL_X, BALL_Y), BALL_RADIUS, BALL_COLOR);

        ball.setMotion(Motion.createDisplacement(BALL_POWER, BALL_ANGLE));

        world.add(ball);
        world.setMaxMoveCount(TOTAL_FRAME);
        world.setDelay(DELAY);

        frame.add(world);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(FRAME_X, FRAME_Y);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setVisible(true);

        ball.setBounds(world.getBounds());

        world.run();
    }
}
