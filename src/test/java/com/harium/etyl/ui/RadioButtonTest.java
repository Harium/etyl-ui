package com.harium.etyl.ui;

import org.junit.Assert;
import org.junit.Test;

public class RadioButtonTest {

    @Test
    public void testInit() {
        RadioButton button = new RadioButton(0, 0, 10, 10);
        Assert.assertFalse(button.isChecked());
    }

}
