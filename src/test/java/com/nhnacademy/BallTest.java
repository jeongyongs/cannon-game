package com.nhnacademy;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.awt.Color;
import java.awt.Point;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

public class BallTest {

    @RepeatedTest(10)
    public void testBallCreation() {
        // given
        final int x = (int) (1 + Math.random() * 20);
        final int y = (int) (1 + Math.random() * 20);
        final int radius = (int) (1 + Math.random() * 20);
        final Color color = Color.BLUE;

        // when
        Ball ball = new Ball(new Point(x, y), radius, color);
        Ball ball2 = new Ball(new Point(x, y), radius);

        // then
        assertAll("testBallCreation",
                () -> assertEquals(new Point(x, y), ball.getLocation()),
                () -> assertEquals(color, ball.getColor()),
                () -> assertEquals(radius, ball.getRadius()),
                () -> assertEquals(new Point(x, y), ball2.getLocation()),
                () -> assertEquals(Color.BLACK, ball2.getColor()),
                () -> assertEquals(radius, ball2.getRadius()));
    }

    @Test
    public void testMutatorAndAccessor() {
        // given
        Ball ball = new Ball(new Point(10, 10), 10);

        // when
        ball.setColor(Color.RED);
        ball.setLocation(new Point(20, 20));
        ball.setRadius(20);

        // then
        assertAll("testMutatorAndAccessor",
                () -> assertEquals(Color.RED, ball.getColor()),
                () -> assertEquals(new Point(20, 20), ball.getLocation()),
                () -> assertEquals(20, ball.getRadius()));
    }
}
