package com.rappytv.widgets.gui.widgets;

import com.rappytv.widgets.WidgetAddon;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;

public class GuiWidget extends TextHudWidget<TextHudWidgetConfig> {

    private final GuiWidgetProperties properties;

    public GuiWidget(GuiWidgetProperties widget) {
        super("custom_widget_" + widget.getID());
        super.bindCategory(new GuiWidgetCategory());
        this.properties = widget;
    }

    public void load(TextHudWidgetConfig config) {
        super.load(config);
        super.createLine(properties.getLabel(), properties.getValue());
        super.setIcon(WidgetAddon.widgetIcon);
    }
}