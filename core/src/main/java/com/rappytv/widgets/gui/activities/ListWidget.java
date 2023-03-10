package com.rappytv.widgets.gui.activities;

import com.rappytv.widgets.gui.widgets.GuiWidgetProperties;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.gui.lss.property.annotation.AutoWidget;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.widget.SimpleWidget;
import net.labymod.api.client.gui.screen.widget.widgets.ComponentWidget;
import net.labymod.api.client.gui.screen.widget.widgets.renderer.IconWidget;
import net.labymod.api.client.resources.ResourceLocation;

@AutoWidget
public class ListWidget extends SimpleWidget {

    private GuiWidgetProperties properties;

    public ListWidget(GuiWidgetProperties properties) {
        this.properties = properties;
    }

    public void initialize(Parent parent) {
        super.initialize(parent);

        ResourceLocation resourceLocation = ResourceLocation.create("widgets", "sprites.png");
        IconWidget iconWidget = new IconWidget(Icon.sprite16(resourceLocation, 0, 0));
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

//    public void setProperties(GuiWidgetProperties properties) {
//        this.properties = properties;
//    }
}
