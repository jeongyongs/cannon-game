package com.nhnacademy;

import java.awt.Point;
import java.awt.Rectangle;

public interface Regionable {

    public enum Type {
        Wall,
        Target
    }

    public Rectangle getRegion();

    public void setLocation(Point location);

    public Type getType();

    public void setType(Type type);
}
