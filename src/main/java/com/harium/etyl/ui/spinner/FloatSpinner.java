package com.harium.etyl.ui.spinner;

import com.harium.etyl.ui.Spinner;

public class FloatSpinner extends Spinner<Float> {

    public FloatSpinner(int x, int y, int w, int h) {
        super(x, y, w, h);
        this.value = 0f;
        this.step = 1f;

        this.minValue = Float.MIN_VALUE;
        this.maxValue = Float.MAX_VALUE;
    }

    @Override
    public void add() {
        if (value.floatValue() + step.floatValue() <= maxValue) {
            this.value = value.floatValue() + step.floatValue();
        }
    }

    @Override
    public void subtract() {
        if (value.floatValue() - step.floatValue() >= minValue) {
            this.value = value.floatValue() - step.floatValue();
            // Fix round
            if (value < minValue + step) {
                value = minValue;
            }
        }
    }

    public Float getValue() {
        return this.value.floatValue();
    }

}


