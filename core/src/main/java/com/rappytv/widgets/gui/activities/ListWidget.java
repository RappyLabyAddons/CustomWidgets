package com.rappytv.widgets.gui.activities;

import com.rappytv.widgets.WidgetAddon;
import com.rappytv.widgets.gui.widgets.GuiWidgetProperties;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.lss.property.annotation.AutoWidget;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.widget.SimpleWidget;
import net.labymod.api.client.gui.screen.widget.widgets.ComponentWidget;
import net.labymod.api.client.gui.screen.widget.widgets.renderer.IconWidget;

@AutoWidget
public class ListWidget extends SimpleWidget {

    private final GuiWidgetProperties properties;

    public ListWidget(GuiWidgetProperties properties) {
        this.properties = properties;
    }

    public void initialize(Parent parent) {
        super.initialize(parent);

        IconWidget iconWidget = new IconWidget(WidgetAddon.widgetIcon);
        iconWidget.addId("avatar");
        this.addChild(iconWidget);
        ComponentWidget labelWidget = ComponentWidget.component(Component.text(this.properties.getLabel()));
        labelWidget.addId("label");
        this.addChild(labelWidget);
        ComponentWidget valueWidget = ComponentWidget.component(Component.text(this.properties.getValue()));
        valueWidget.addId("value");
        this.addChild(valueWidget);
    }

    public GuiWidgetProperties getProperties() {
        return this.properties;
    }
}
