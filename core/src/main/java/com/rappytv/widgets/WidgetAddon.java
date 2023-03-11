package com.rappytv.widgets;

import com.rappytv.widgets.gui.widgets.GuiWidget;
import com.rappytv.widgets.gui.widgets.GuiWidgetCategory;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class WidgetAddon extends LabyAddon<WidgetAddonConfiguration> {

    private static WidgetAddon instance;
    public static Icon widgetIcon;

    @Override
    protected void enable() {
        instance = this;
        /*****************************
            Original texture:
            Namespace: 'labymod'
            Path: 'themes/vanilla/textures/settings/main/laby_1.png'
            Slots: X4;Y2
        *****************************/
        ResourceLocation resource = ResourceLocation.create("widgets", "themes/vanilla/textures/settings/hud/hud.png");
        widgetIcon = Icon.sprite32(resource, 0, 0);

        registerSettingCategory();
        labyAPI().hudWidgetRegistry().categoryRegistry().register(new GuiWidgetCategory());
        configuration().getWidgets().forEach((ID, widget) -> WidgetAddon.get().labyAPI().hudWidgetRegistry().register(new GuiWidget(widget)));
    }

    public static WidgetAddon get() {
        return instance;
    }

    @Override
    protected Class<? extends WidgetAddonConfiguration> configurationClass() {
        return WidgetAddonConfiguration.class;
    }
}
