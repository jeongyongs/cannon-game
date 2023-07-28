package com.nhnacademy;

import java.awt.Rectangle;

public interface Bouncable {
    public Rectangle getBounds();

    public void setBounds(Rectangle bounds);

    public boolean isOutOfBounds();

    public void bounce();
}
