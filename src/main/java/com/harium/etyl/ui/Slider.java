package com.harium.etyl.ui;

import com.harium.etyl.ui.base.BaseSlider;
import com.harium.etyl.ui.base.UIView;
import com.harium.etyl.ui.listener.OnValueChangeListener;
import com.harium.etyl.ui.theme.ThemeManager;

public class Slider extends UIView {

    private BaseSlider slider;

    public Slider(int x, int y, int w, int h) {
        super(x, y, w, h);

        this.slider = ThemeManager.getInstance().getTheme().createSlider(x, y, w, h);
        delegateView(slider);
    }

    public void rebuild() {
        BaseSlider view = ThemeManager.getInstance().getTheme().createSlider(x, y, w, h);
        view.copy(delegatedView);

        delegateView(view);
    }

    public float getMinValue() {
        return slider.getMinValue();
    }

    public void setMinValue(float minValue) {
        slider.setMinValue(minValue);
    }

    public float getMaxValue() {
        return slider.getMaxValue();
    }

    public void setMaxValue(float maxValue) {
        slider.setMaxValue(maxValue);
    }

    public float getValue() {
        return slider.getValue();
    }

    public void setValue(float value) {
        slider.setValue(value);
    }

    public void setOnValueChangeListener(OnValueChangeListener listener) {
        slider.setOnValueChangeListener(listener);
    }

}

