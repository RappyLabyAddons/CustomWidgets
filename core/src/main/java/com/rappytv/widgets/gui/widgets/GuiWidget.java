package com.rappytv.widgets.gui.widgets;

import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;

public class GuiWidget extends TextHudWidget<TextHudWidgetConfig> {

    private TextLine line;
    private final GuiWidgetProperties properties;

    public GuiWidget(GuiWidgetProperties widget) {
        super("custom_widget_" + widget.getID());
        super.bindCategory(new GuiWidgetCategory());
        this.properties = widget;
    }

    public void load(TextHudWidgetConfig config) {
        super.load(config);
        this.line = super.createLine(properties.getLabel(), properties.getValue());
        this.setIcon(null);
    }

    public void updateLine(GuiWidgetProperties properties) {
        this.properties.setLabel(properties.getLabel());
        this.properties.setValue(properties.getValue());
        this.line.updateAndFlush(properties.getValue());
    }
}
