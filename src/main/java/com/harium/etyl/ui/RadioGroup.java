package com.harium.etyl.ui;

import com.harium.etyl.ui.base.BaseRadioButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RadioGroup {

    public static final String DEFAULT_GROUP = "";
    private String name = "";
    private BaseRadioButton checked = null;
    private List<BaseRadioButton> radios = new ArrayList<>();

    protected static Map<String, RadioGroup> radioGroups = new HashMap<>();

    public RadioGroup(String name) {
        super();
        this.name = name;
    }

    public static void add(String groupName, BaseRadioButton radio) {
        RadioGroup group = getRadioGroup(groupName);
        group.add(radio);
        radio.setGroup(group);
    }

    public static void setGroup(String groupName, BaseRadioButton radio) {
        RadioGroup group = radio.getGroup();
        if (group != null && !group.isEmpty()) {
            removeRadioButton(radio);
        }
        add(groupName, radio);
    }

    public void add(BaseRadioButton radio) {
        this.radios.add(radio);
        radio.setGroup(this);
    }

    public void remove(BaseRadioButton radio) {
        this.radios.remove(radio);
        if (checked == radio) {
            checked = null;
        }
    }

    public void checkRadio(BaseRadioButton radio) {
        this.checked = radio;

        for (BaseRadioButton rad : radios) {
            if (rad != radio) {
                if (rad.isChecked()) {
                    rad.setChecked(false);
                    rad.getOnCheckListener().onCheck(false);
                }
            }
        }
        radio.setChecked(true);
        radio.getOnCheckListener().onCheck(true);
    }

    public void uncheckRadio(BaseRadioButton radio) {
        if (this.checked == radio) {
            radio.setChecked(false);
            radio.getOnCheckListener().onCheck(false);
        }
    }

    public BaseRadioButton getChecked() {
        return checked;
    }

    public String getValue() {
        if (checked != null) {
            return checked.getValue();
        }

        return "";
    }

    public boolean isEmpty() {
        return radios.isEmpty();
    }

    public static RadioGroup getRadioGroup(String groupName) {
        if (!radioGroups.containsKey(groupName)) {
            radioGroups.put(groupName, new RadioGroup(groupName));
        }
        return radioGroups.get(groupName);
    }

    protected static void removeRadioButton(BaseRadioButton radio) {
        //String groupName = radio.getGroup();
        RadioGroup group = radio.getGroup();
        if (group != null) {
            group.remove(radio);
            // Remove group from map
            if (group.isEmpty()) {
                radioGroups.remove(group.name);
            }
        }
    }

    public static boolean isChecked(RadioGroup group, BaseRadioButton baseRadioButton) {
        return baseRadioButton == group.getChecked();
    }

    public static boolean isChecked(BaseRadioButton radio) {
        RadioGroup group = radio.getGroup();
        return RadioGroup.isChecked(group, radio);
    }

    public static void check(BaseRadioButton radio) {
        RadioGroup group = radio.getGroup();
        group.checkRadio(radio);
    }

    public static void uncheck(BaseRadioButton radio) {
        RadioGroup group = radio.getGroup();
        group.uncheckRadio(radio);
    }

    public String getName() {
        return name;
    }

    public List<BaseRadioButton> getRadios() {
        return radios;
    }

    public static void clear() {
        radioGroups.clear();
        radioGroups.put(DEFAULT_GROUP, new RadioGroup(DEFAULT_GROUP));
    }
}
