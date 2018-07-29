package com.harium.etyl.ui.theme.base;

import com.harium.theme.base.BaseTheme;
import org.junit.Assert;
import org.junit.Test;

public class BaseThemeTest {

    @Test
    public void testConstructor() {
        BaseTheme theme = new BaseTheme();
        Assert.assertNotNull(theme);
    }

}
