package com.nhnacademy;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;

public class MotionTest {
    @Test
    public void testMotion() {
        // given
        final int dX = 10;
        final int dY = 10;
        final int magnitude = 10;
        final int angle = 30;

        // when
        Motion motionByPosition = Motion.createPosition(dX, dY);
        Motion motionByDisplacement = Motion.createDisplacement(magnitude, angle);

        // then
        assertAll("testMotion",
                () -> assertEquals(dX, motionByPosition.getDX()),
                () -> assertEquals(dY, motionByPosition.getDY()),
                () -> assertEquals(8, motionByDisplacement.getDX()),
                () -> assertEquals(4, motionByDisplacement.getDY()));
    }
}
