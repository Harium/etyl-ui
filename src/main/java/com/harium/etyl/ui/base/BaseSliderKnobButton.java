package com.harium.etyl.ui.base;

import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.Button;
import com.harium.etyl.ui.View;


public class BaseSliderKnobButton extends BaseSlider {

    protected View knob;

    public BaseSliderKnobButton(int x, int y, int w, int h) {
        super(x, y, w, h);

        buildButton();
        sliderPosition = getX() - knob.getW() / 2;
        knob.setX(sliderPosition);
    }

    public void buildButton() {
        knob = new Button(x, y, h / 4, h);
    }

    public void rebuild() {
        knob.rebuild();
        knob.setX(sliderPosition);
    }

    @Override
    public GUIEvent updateMouse(PointerEvent event) {
        GUIEvent value = super.updateMouse(event);

        knob.setMouseOver(mouseOver);

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
            sliderPosition = getX() - knob.getW() / 2;
        } else if (value > maxValue) {
            value = maxValue;
            sliderPosition = getX() + getW() - knob.getW() / 2;
        } else {
            sliderPosition = event.getX() - knob.getW() / 2;
        }

        knob.setX(sliderPosition);
    }

    @Override
    public void updateEvent(GUIEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        //Draw Knob
        knob.draw(g);
    }

    protected void updateSliderPosition() {
        super.updateSliderPosition();
        knob.setX(sliderPosition - knob.getW() / 2);
    }

}

