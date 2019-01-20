package com.harium.etyl.ui.spinner;

import com.harium.etyl.ui.Spinner;

public class IntegerSpinner extends Spinner<Integer> {

    public IntegerSpinner(int x, int y, int w, int h) {
        super(x, y, w, h);
        this.value = 0;
        this.step = 1;

        this.minValue = Integer.MIN_VALUE;
        this.maxValue = Integer.MAX_VALUE;
    }

    @Override
    public void add() {
        if (value + step <= maxValue) {
            this.value = value + step;
        }
    }

    @Override
    public void subtract() {
        if (value - step >= minValue) {
            this.value = value - step;
        }
    }

    public Integer getValue() {
        return this.value;
    }

}


