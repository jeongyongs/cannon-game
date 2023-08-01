package com.nhnacademy;

import java.awt.Rectangle;
import java.util.function.Predicate;

public interface Bouncable {
    public Rectangle getBounds();

    public void setBounds(Rectangle bounds);

    public boolean isOutOfBounds();

    public void bounce(Predicate<Object> predicate, Runnable runnable);
}
