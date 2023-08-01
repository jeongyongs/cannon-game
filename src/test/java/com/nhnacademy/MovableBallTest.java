package com.nhnacademy;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import java.awt.Point;
import java.util.Random;
import org.junit.jupiter.api.Test;

public class MovableBallTest {

    @Test
    public void testMovement() {
        // given
        final Random random = new Random();
        final int x = random.nextInt(100);
        final int y = random.nextInt(100);
        final int radius = random.nextInt(100);
        final int dX = random.nextInt(100);
        final int dY = random.nextInt(100);
        final MovableWorld world = new MovableWorld();

        // when
        MovableBall ball = new MovableBall(new Point(x, y), radius);
        ball.setMotion(Motion.createPosition(dX, dY));
        ball.setWorld(world);
        ball.move();

        // then
        assertAll("testMovement",
                () -> assertEquals(dX, ball.getMotion().getDX()),
                () -> assertEquals(dY, ball.getMotion().getDY()),
                () -> assertEquals(x + dX, (int) ball.getLocation().getX()),
                () -> assertEquals(y + dY, (int) ball.getLocation().getY()));
    }
}
