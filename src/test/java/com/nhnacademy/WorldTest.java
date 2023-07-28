package com.nhnacademy;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertAll;
import java.awt.Point;
import javax.swing.JFrame;
import org.junit.jupiter.api.Test;

public class WorldTest {

    private static WorldTest worldTest = new WorldTest();

    public static void main(String[] args) {
        worldTest.testVisible();
    }

    private void testVisible() {
        JFrame frame = new JFrame();
        World world = new World();
        Regionable ball = new Ball(new Point(50, 50), 50);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(500, 300);
        frame.setSize(500, 300);

        frame.add(world);
        world.add(ball);

        frame.setVisible(true);
    }

    @Test
    public void testWorldCreation() {
        // given
        Regionable ball = new Ball(new Point(50, 50), 50);

        // when
        World world = new World();
        world.add(ball);

        // then
        assertAll("testWorldCreation",
                () -> assertEquals(ball, world.get(0)),
                () -> assertEquals(1, world.getCount()));
    }
}
