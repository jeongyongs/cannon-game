package com.nhnacademy;

import java.awt.Rectangle;

public class BoundedWorld extends MovableWorld {

    public boolean outOfBounds(Regionable item) {
        Rectangle intersection = item.getRegion().intersection(getBounds());

        return intersection.getWidth() != item.getRegion().getWidth()
                || intersection.getHeight() != item.getRegion().getHeight();
    }

    public void bounceBall(Movable item) {
        Regionable regionableItem = (Regionable) item;

        if (regionableItem.getRegion().getMinX() < getBounds().getMinX()) {
            item.getMotion().setDX(Math.abs(item.getMotion().getDX()));
        }
        if (regionableItem.getRegion().getMaxX() > getBounds().getMaxX()) {
            item.getMotion().setDX(-Math.abs(item.getMotion().getDX()));
        }
        if (regionableItem.getRegion().getMinY() < getBounds().getMinY()) {
            item.getMotion().setDY(Math.abs(item.getMotion().getDY()));
        }
        if (regionableItem.getRegion().getMaxY() > getBounds().getMaxY()) {
            item.getMotion().setDY(-Math.abs(item.getMotion().getDY()));

            // if (Math.abs(item.getMotion().getDY()) < 2) { // 이탈 방지
            // regionableItem.setLocation(new Point((int)
            // regionableItem.getRegion().getCenterX(),
            // (int) (getBounds().getMaxY() - regionableItem.getRegion().getHeight() / 2)));
            // }
            // if (item.getMotion().getDX() != 0) {
            // item.getMotion().setDX(item.getMotion().getDX()
            // - (item.getMotion().getDX() / Math.abs(item.getMotion().getDX())));
            // }
        }
    }

    @Override
    public void refresh() {
        super.refresh();

        getItems().stream()
                .filter(item -> item instanceof Movable && outOfBounds(item))
                .forEach(item -> bounceBall((Movable) item));
    }
}
