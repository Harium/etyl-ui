package com.harium.etyl.ui.base;

import com.harium.etyl.commons.event.GUIEvent;
import com.harium.etyl.core.graphics.Graphics;
import com.harium.etyl.ui.RadioGroup;
import com.harium.etyl.ui.listener.OnCheckListener;

/**
 * Radio Button component
 */

public class BaseRadioButton extends BaseCheckBox {

    private RadioGroup group;
    private String value;
    private OnCheckListener onCheckListener = NULL_ON_CHECK_LISTENER;

    public BaseRadioButton(int x, int y) {
        this(x, y, 22, 22);
    }

    public BaseRadioButton(int x, int y, int w, int h) {
        super(x, y, w, h);
        setGroup(RadioGroup.DEFAULT_GROUP);
    }

    public BaseRadioButton(int x, int y, String groupName) {
        this(x, y);
        setGroup(groupName);
    }

    @Override
    protected void leftDown() {
        check();
    }

    @Override
    public void updateEvent(GUIEvent event) {
        // TODO Auto-generated method stub

    }

    @Override
    public void draw(Graphics g) {
        g.setColor(getTheme().getBackgroundColor());
        g.fillOval(x, y, w, h);

        if (!mouseOver) {
            g.setColor(getTheme().getTextFieldWithoutFocusColor());
        } else {
            g.setColor(getTheme().getTextFieldOnMouseColor());
        }

        g.drawOval(x, y, w, h);

        g.setColor(getTheme().getTextFieldWithoutFocusColor());

        if (isChecked()) {
            drawCheck(g);
        }
    }

    protected void drawCheck(Graphics g) {
        int radius = w / 3;
        g.fillCircle(x, y, radius);
    }

    public RadioGroup getGroup() {
        return group;
    }

    public String getGroupName() {
        return group.getName();
    }

    public void setGroup(String group) {
        RadioGroup.setGroup(group, this);
    }

    @Override
    public boolean onMouse(int mx, int my) {
        return collideCirclePoint(mx, my);
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean isChecked() {
        return RadioGroup.isChecked(group, this);
    }

    public void check() {
        group.checkRadio(this);
    }

    public void uncheck() {
        group.uncheckRadio(this);
    }

    public void copy(BaseRadioButton view) {
        super.copy(view);
        group = view.group;
        value = view.value;
        onCheckListener = view.onCheckListener;
    }

    public OnCheckListener getOnCheckListener() {
        return onCheckListener;
    }

    public void setOnCheckListener(OnCheckListener onCheckListener) {
        this.onCheckListener = onCheckListener;
    }

    public void setGroup(RadioGroup group) {
        this.group = group;
    }
}
