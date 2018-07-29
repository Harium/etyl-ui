package com.harium.etyl.ui.icon;

import com.harium.etyl.core.graphics.Graphics;

public class UpArrow extends ArrowIcon {

    public UpArrow(int x, int y) {
        super(x, y);
    }

    public UpArrow(int x, int y, float size) {
        super(x, y, size);
    }

    @Override
    public void draw(Graphics g) {
        int halfW = (int) (size / 2);
        int h = (int) (size / 4);

        g.drawLine(x, y + h, x + halfW, y);
        g.drawLine(x + halfW, y, x + halfW * 2, y + h);
    }

}
