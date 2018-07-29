package com.harium.etyl.ui.icon;

import com.harium.etyl.core.graphics.Graphics;

public class DownArrow extends ArrowIcon {

    public DownArrow(int x, int y) {
        super(x, y);
    }

    public DownArrow(int x, int y, float size) {
        super(x, y, size);
    }

    @Override
    public void draw(Graphics g) {
        int halfW = (int) (size / 2);
        int h = (int) (size / 4);

        g.drawLine(x, y, x + halfW, y + h);
        g.drawLine(x + halfW, y + h, x + halfW * 2, y);
    }

}
