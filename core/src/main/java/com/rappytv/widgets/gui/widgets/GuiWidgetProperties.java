package com.rappytv.widgets.gui.widgets;

import java.util.Date;

public class GuiWidgetProperties {

    private final int id;
    private String label;
    private String value;

    public GuiWidgetProperties(int id, String label, String value) {
        this.id = id;
        this.label = label;
        this.value = value;
    }

    public static GuiWidgetProperties createDefault() {
        return new GuiWidgetProperties((int) new Date().getTime(), "", "");
    }
    public int getID() {
        return id;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public String getValue() {
        return value;
    }
    public void setValue(String value) {
        this.value = value;
    }
}
