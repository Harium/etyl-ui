package com.harium.etyl.ui.base;

import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.View;

public class BaseDivider extends View {

    public BaseDivider(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public GUIEvent updateKeyboard(KeyEvent event) {
        return GUIEvent.NONE;
    }

    @Override
    public void updateEvent(GUIEvent event) {

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getTheme().getBorderColor());
        g.fillRect(left(), top(), width(), height());
    }
}
