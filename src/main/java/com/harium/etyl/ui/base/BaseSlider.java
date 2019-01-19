package com.harium.etyl.ui.base;

import com.harium.etyl.commons.event.*;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.View;
import com.harium.etyl.ui.theme.Theme;


public class BaseSlider extends View {

    protected int sliderPosition = 0;

    protected float minValue = 0;
    protected float maxValue = 255;

    protected float value = 0;
    protected boolean activated = false;

    public BaseSlider(int x, int y, int w, int h) {
        super(x, y, w, h);

        deactivate();
        sliderPosition = getX();
    }

    @Override
    public GUIEvent updateMouse(PointerEvent event) {
        GUIEvent value = super.updateMouse(event);

        if (mouseOver) {
            // Only on first pressed
            if (event.getState() == PointerState.PRESSED && event.isButtonDown(MouseEvent.MOUSE_BUTTON_LEFT)) {
                activate();
                activated = true;
            }
        }

        if (activated) {
            // Pressed or Dragged
            if (event.isButtonDown(MouseEvent.MOUSE_BUTTON_LEFT)) {
                updateValue(event);
                return GUIEvent.COMPONENT_CHANGED;
            } else if (event.isButtonUp(MouseEvent.MOUSE_BUTTON_LEFT)) {
                activated = false;
                deactivate();
            }
        }

        return value;
    }

    protected void activate() {

    }

    protected void deactivate() {

    }

    public void updateValue(PointerEvent event) {
        value = calculateValue(event.getX());

        if (value < minValue) {
            value = minValue;
            sliderPosition = getX();
        } else if (value > maxValue) {
            value = maxValue;
            sliderPosition = getX() + getW();
        } else {
            sliderPosition = event.getX();
        }
    }

    @Override
    public void updateEvent(GUIEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void draw(Graphics g) {
        if (!visible)
            return;

        //Draw Slide
        Theme theme = getTheme();

        g.setColor(theme.getBarColor());

        int sh = h / 5;
        g.fillRect(x, y + h / 2 - sh / 2, w, sh);
    }

    public float getMinValue() {
        return minValue;
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
        updateSliderPosition();
    }

    public float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
        updateSliderPosition();
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
        updateSliderPosition();
    }

    @Override
    public GUIEvent updateKeyboard(KeyEvent event) {
        if (onFocus) {
            if (event.isKeyDown(KeyEvent.VK_RIGHT)) {
                setValue(value + 1);
            }
            if (event.isKeyDown(KeyEvent.VK_LEFT)) {
                setValue(value + 1);
            }
        }

        return GUIEvent.NONE;
    }

    /* package*/ float calculateValue(int x) {
        int mx = x - getX();
        float interval = maxValue - minValue;
        float factor = mx / (float) w;

        return interval * factor + minValue;
    }

    protected void updateSliderPosition() {
        float interval = maxValue - minValue;
        float bx = x + (((value - minValue) * w) / interval);
        sliderPosition = (int) bx;
    }

}

