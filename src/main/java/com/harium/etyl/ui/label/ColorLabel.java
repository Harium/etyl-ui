package com.harium.etyl.ui.label;

import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.Label;


public class ColorLabel extends Label {

    public ColorLabel(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    public ColorLabel(Color color, int w, int h) {
        super(0, 0, w, h);

        this.color = color;
    }

    private Color color;

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, w, h);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

}