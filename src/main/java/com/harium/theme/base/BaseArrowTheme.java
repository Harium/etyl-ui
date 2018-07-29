package com.harium.theme.base;

import com.harium.etyl.commons.event.MouseState;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.theme.ArrowDrawer;
import com.harium.etyl.ui.theme.ArrowTheme;
import com.harium.etyl.ui.theme.Theme;
import com.harium.etyl.ui.theme.cursor.arrow.MouseArrow;

public class BaseArrowTheme implements ArrowDrawer, ArrowTheme {

    private BaseArrow arrow;

    public BaseArrowTheme() {
        arrow = new BaseArrow();
    }

    public void drawTimedIndicator(Graphics g, float amount) {

    }

    public void setLocation(int mouseX, int mouseY) {
        arrow.setLocation(mouseX, mouseY);
    }

    public void draw(Graphics g) {
        arrow.draw(g);
    }

    public void changeState(MouseState state) {

    }

    public void updateArrowTheme(ArrowTheme arrowTheme) {

    }

    public MouseArrow getNormalArrow() {
        return arrow;
    }

    public MouseArrow getClickArrow() {
        return arrow;
    }

    public MouseArrow getLinkArrow() {
        return arrow;
    }

    public MouseArrow getHelpArrow() {
        return arrow;
    }

    public MouseArrow getTextArrow() {
        return arrow;
    }

    public MouseArrow getWaitArrow() {
        return arrow;
    }

    public MouseArrow getMoveArrow() {
        return arrow;
    }

    public MouseArrow getHorizontalArrow() {
        return arrow;
    }

    public MouseArrow getVerticalArrow() {
        return arrow;
    }

    public MouseArrow getDiagonalArrow() {
        return arrow;
    }

    public MouseArrow getInvertedDiagonalArrow() {
        return arrow;
    }
}
