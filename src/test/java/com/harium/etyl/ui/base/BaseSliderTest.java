package com.harium.etyl.ui.base;

import com.harium.etyl.commons.event.MouseEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.commons.event.PointerState;
import org.junit.Assert;
import org.junit.Test;

public class BaseSliderTest {

    @Test
    public void testUpdateValue() {
        BaseSlider slider = new BaseSlider(0,0,100,1);
        slider.setMaxValue(1);
        slider.setMinValue(0);

        PointerEvent event = new PointerEvent(MouseEvent.MOUSE_BUTTON_LEFT, PointerState.PRESSED, 50,0);
        slider.updateValue(event);

        Assert.assertEquals(0.5f, slider.getValue(), 0);
    }

}
