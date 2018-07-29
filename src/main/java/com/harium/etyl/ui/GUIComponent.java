package com.harium.etyl.ui;

import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.commons.event.KeyEvent;
import com.harium.etyl.commons.event.PointerEvent;

interface GUIComponent {

    /**
     * @param event
     * @return
     */
    GUIEvent updateMouse(PointerEvent event);

    /**
     * @param event
     * @return
     */
    GUIEvent updateKeyboard(KeyEvent event);

    /**
     * @param event
     */
    void updateEvent(GUIEvent event);

    /**
     * @param mx - mouse coordinate x
     * @param my - mouse coordinate y
     * @return
     */
    boolean onMouse(int mx, int my);

    /**
     * @param width  - new window width
     * @param height - new window height
     */
    void resize(int width, int height);

}
