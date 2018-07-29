package com.harium.etyl.ui.theme;

import com.harium.etyl.commons.Drawable;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.core.input.mouse.MouseStateListener;
import com.harium.etyl.ui.theme.listener.ArrowThemeListener;


public interface ArrowDrawer extends MouseStateListener, Drawable, ArrowThemeListener {
    /**
     * Method to draw a timed cursor (time varies from 0 to 1)
     *
     * @param g      - Graphics
     * @param amount - value between 0 and 1
     */
    void drawTimedIndicator(Graphics g, float amount);

    void setLocation(int mouseX, int mouseY);
}
