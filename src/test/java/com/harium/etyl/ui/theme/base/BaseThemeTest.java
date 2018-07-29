package com.harium.etyl.ui.theme.base;

import com.harium.theme.base.BaseArrowTheme;
import org.junit.Assert;
import org.junit.Test;

public class BaseThemeTest {

    @Test
    public void testConstructor() {
        BaseArrowTheme theme = new BaseArrowTheme();
        Assert.assertNotNull(theme);
    }

}
