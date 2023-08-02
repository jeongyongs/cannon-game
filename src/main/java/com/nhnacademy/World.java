package com.nhnacademy;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class World extends JPanel {

    private List<Regionable> items;

    public World() {
        items = new ArrayList<>();
    }

    protected List<Regionable> getItems() {
        return items;
    }

    protected void setItems(List<Regionable> items) {
        this.items = items;
    }

    protected void add(Regionable item) {
        items.add(item);
    }

    protected int getCount() {
        return items.size();
    }

    protected Regionable get(int index) {
        return items.get(index);
    }

    protected void remove(Regionable item) {
        items.remove(item);
    }

    @Override
    public void remove(int index) {
        items.remove(index);
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        items.stream()
                .forEach(item -> ((Paintable) item).paint(graphics));
    }
}
