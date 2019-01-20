package com.harium.etyl.ui;

import com.harium.etyl.ui.base.BaseRadioButton;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RadioGroupTest {

    @Before
    public void setUp() {
        UI.clear();
    }

    @Test
    public void testInit() {
        Assert.assertEquals(0, RadioGroup.radioGroups.size());
    }

    @Test
    public void testAdd() {
        BaseRadioButton button = new BaseRadioButton(0, 0, "group1");
        BaseRadioButton button2 = new BaseRadioButton(0, 0, "group1");

        Assert.assertEquals(1, RadioGroup.radioGroups.size());
    }

    @Test
    public void testRemove() {
        BaseRadioButton button = new BaseRadioButton(0, 0, "group1");
        BaseRadioButton button2 = new BaseRadioButton(0, 0, "group1");

        UI.remove(button);
        Assert.assertEquals(1, button2.getGroup().getRadios().size());

        UI.remove(button2);
        Assert.assertEquals(0, button2.getGroup().getRadios().size());
        Assert.assertEquals(0, RadioGroup.radioGroups.size());
    }

    @Test
    public void testChangeRadioGroup() {
        BaseRadioButton button = new BaseRadioButton(0, 0, "group1");
        button.setGroup("group2");
        Assert.assertEquals(1, RadioGroup.radioGroups.size());

        UI.remove(button);
        Assert.assertEquals(0, RadioGroup.radioGroups.size());
    }

}
