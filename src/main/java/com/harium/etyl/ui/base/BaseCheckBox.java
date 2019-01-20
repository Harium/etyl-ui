package com.harium.etyl.ui.base;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.Label;
import com.harium.etyl.ui.listener.OnCheckListener;

/**
 * Checkbox component
 */

public class BaseCheckBox extends BaseButton {

    protected boolean checked = false;
    protected OnCheckListener onCheckListener = NULL_ON_CHECK_LISTENER;

    public BaseCheckBox(int x, int y) {
        this(x, y, 22, 22);
    }

    public BaseCheckBox(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    protected void leftUp() {
        swapChecked();
    }

    private void swapChecked() {
        checked = !checked;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getTheme().getBackgroundColor());
        g.fillRect(x, y, w, h);

        if (!mouseOver) {
            g.setColor(getTheme().getTextFieldWithoutFocusColor());
        } else {
            g.setColor(getTheme().getTextFieldOnMouseColor());
        }

        g.drawRect(x, y, w, h);

        if (isChecked()) {
            drawCheck(g);
        }
    }

    protected void drawCheck(Graphics g) {
        int radius = w / 3;
        g.fillCircle(x, y, radius);
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }


    public void copy(BaseCheckBox view) {
        super.copy(view);
        checked = view.checked;
        onCheckListener = view.onCheckListener;
    }

    public OnCheckListener getOnCheckListener() {
        return onCheckListener;
    }

    public void setOnCheckListener(OnCheckListener onCheckListener) {
        this.onCheckListener = onCheckListener;
    }
}
