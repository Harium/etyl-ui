package com.harium.etyl.ui.theme.base;

import com.harium.etyl.commons.graphics.Color;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.theme.cursor.arrow.MouseArrow;

public class BaseArrow implements MouseArrow {

    private int mx, my;

    // TODO Change logic to BufferedLayer (or Pixmap...)
    @Override
    public void draw(Graphics g) {
        // Draw Basic Cursor
        g.setColor(Color.BLACK);
        g.drawLine(mx, my, mx, my + 26);
        g.drawLine(mx, my + 26, mx + 7, my + 21);

        g.drawLine(mx + 7, my + 21, mx + 11, my + 32);
        g.drawLine(mx + 11, my + 32, mx + 16, my + 30);
        g.drawLine(mx + 16, my + 30, mx + 11, my + 20);
        g.drawLine(mx + 11, my + 20, mx + 19, my + 20);

        g.drawLine(mx + 19, my + 20, mx, my);
    }

    @Override
    public void setLocation(int x, int y) {
        this.mx = x;
        this.my = y;
    }
}
