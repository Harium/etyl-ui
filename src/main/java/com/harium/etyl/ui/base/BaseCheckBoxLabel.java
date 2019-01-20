package com.harium.etyl.ui.base;

import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.Label;
import com.harium.etyl.ui.label.BaseCheckerLabel;
import com.harium.etyl.ui.listener.OnCheckListener;

/**
 * Checkbox component
 */

public class BaseCheckBoxLabel extends BaseCheckBox {

    protected Label checkLabel;

    public BaseCheckBoxLabel(int x, int y) {
        this(x, y, 22, 22);
    }

    public BaseCheckBoxLabel(int x, int y, int w, int h) {
        super(x, y, w, h);
        checkLabel = new BaseCheckerLabel(x, y, w, h);
    }

    protected void drawCheck(Graphics g) {
        checkLabel.draw(g);
    }

    public Label getCheckLabel() {
        return checkLabel;
    }

    public void setCheckLabel(Label checkLabel) {
        this.checkLabel = checkLabel;

        checkLabel.setX(x + (w / 2 - checkLabel.getW() / 2) + checkLabel.getX());
        checkLabel.setY(y + (h / 2 - checkLabel.getH() / 2) + checkLabel.getY());

        checkLabel.setContentBounds(x, y, w, h);
    }

    public void copy(BaseCheckBoxLabel view) {
        super.copy(view);
        checkLabel = view.checkLabel;
    }

    public OnCheckListener getOnCheckListener() {
        return onCheckListener;
    }

    public void setOnCheckListener(OnCheckListener onCheckListener) {
        this.onCheckListener = onCheckListener;
    }
}
