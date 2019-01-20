package com.harium.etyl.ui.base;

import org.junit.Assert;
import org.junit.Test;

public class BaseSliderKnobButtonTest {

    @Test
    public void testSetValues() {
        BaseSliderKnobButton slider = new BaseSliderKnobButton(0, 0, 100, 1);
        slider.setMinValue(0);
        slider.setMaxValue(+1);
        Assert.assertEquals(slider.knob.getW() / 2, slider.knob.getX(), 0);

        slider.setMinValue(-1);
        slider.setMaxValue(+1);
        Assert.assertEquals(slider.getW() / 2 - slider.knob.getW() / 2, slider.knob.getX(), 0);

        slider.setMaxValue(+2);
        slider.setMinValue(-2);
        Assert.assertEquals(slider.getW() / 2 - slider.knob.getW() / 2, slider.knob.getX(), 0);
    }

}
