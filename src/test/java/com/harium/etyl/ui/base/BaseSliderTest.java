package com.harium.etyl.ui.base;

import com.harium.etyl.commons.event.MouseEvent;
import com.harium.etyl.commons.event.PointerEvent;
import com.harium.etyl.commons.event.PointerState;
import org.junit.Assert;
import org.junit.Test;

public class BaseSliderTest {

    @Test
    public void testUpdateValue() {
        BaseSlider slider = new BaseSlider(0, 0, 100, 1);
        slider.setMaxValue(1);
        slider.setMinValue(0);

        PointerEvent event = new PointerEvent(MouseEvent.MOUSE_BUTTON_LEFT, PointerState.PRESSED, 50, 0);
        slider.updateValue(event);

        Assert.assertEquals(0.5f, slider.getValue(), 0);
        Assert.assertEquals(50, slider.sliderPosition);
    }

    @Test
    public void testCalculateValue() {
        BaseSlider slider = new BaseSlider(0, 0, 100, 1);
        slider.setMinValue(-1);
        slider.setMaxValue(+1);
        Assert.assertEquals(0, slider.getValue(), 0);

        slider.setMinValue(1);
        slider.setMaxValue(3);
        Assert.assertEquals(slider.getMinValue(), slider.calculateValue(0), 0);
    }

    @Test
    public void testMoveSlider() {
        BaseSlider slider = new BaseSlider(0, 0, 100, 1);
        slider.setMinValue(-1);
        slider.setMaxValue(+1);

        PointerEvent click = new PointerEvent(MouseEvent.MOUSE_BUTTON_LEFT, PointerState.PRESSED, 0,0);
        slider.updateMouse(click);
        Assert.assertEquals(-1, slider.getValue(), 0);

        PointerEvent move = new PointerEvent(MouseEvent.MOUSE_BUTTON_LEFT, PointerState.MOVE, 50,0);
        slider.updateMouse(move);
        Assert.assertEquals(0, slider.getValue(), 0);
    }

    @Test
    public void testSetValues() {
        BaseSlider slider = new BaseSlider(0, 0, 100, 1);
        slider.setValue(0);
        slider.setMinValue(0);
        slider.setMaxValue(+1);
        Assert.assertEquals(0, slider.getValue(), 0);

        slider.setMinValue(-1);
        slider.setMaxValue(+1);
        Assert.assertEquals(0, slider.getValue(), 0);

        slider.setMaxValue(+2);
        slider.setMinValue(-2);
        Assert.assertEquals(0, slider.getValue(), 0);
    }

    @Test
    public void testMultipleSliders() {
        BaseSlider sliderA = new BaseSlider(0, 0, 100, 1);
        BaseSlider sliderB = new BaseSlider(0, 1, 100, 1);

        PointerEvent event = new PointerEvent();
        event.set(MouseEvent.MOUSE_BUTTON_LEFT, PointerState.PRESSED, 50,0);
        sliderA.updateMouse(event);
        sliderB.updateMouse(event);

        // Move down while pressed
        event.set(MouseEvent.MOUSE_BUTTON_LEFT, PointerState.DRAGGED, 50,1);
        sliderA.updateMouse(event);
        sliderB.updateMouse(event);

        Assert.assertTrue(sliderA.activated);
        Assert.assertFalse(sliderB.activated);
    }

}
