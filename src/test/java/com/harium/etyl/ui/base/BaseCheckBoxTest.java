package com.harium.etyl.ui.base;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BaseCheckBoxTest {

    BaseCheckBox checkBox;

    @Before
    public void setUp() {
        checkBox = new BaseCheckBox(0,0,0,0);
    }

    @Test
    public void testLeftClick() {
        checkBox.setChecked(false);
        checkBox.leftUp();

        Assert.assertTrue(checkBox.checked);
    }

}
