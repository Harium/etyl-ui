package com.harium.etyl.ui;

import com.harium.etyl.ui.base.BaseDivider;
import com.harium.etyl.ui.base.UIView;
import com.harium.etyl.ui.theme.ThemeManager;


public class Divider extends UIView {

    private BaseDivider divider;

    public Divider(int x, int y, int w, int h) {
        super(x, y, w, h);

        this.divider = ThemeManager.getInstance().getTheme().createDivider(x, y, w, h);
        delegateView(divider);
    }

}
