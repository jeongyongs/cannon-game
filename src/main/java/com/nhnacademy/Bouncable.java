package com.nhnacademy;

import java.util.function.Predicate;

public interface Bouncable {

    public boolean isOutOfBounds();

    public void bounce(Predicate<Object> predicate, Runnable runnable);
}
