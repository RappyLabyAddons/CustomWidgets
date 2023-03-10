package com.rappytv.widgets;

import com.rappytv.widgets.gui.activities.ListWidget;
import com.rappytv.widgets.gui.activities.WidgetActivity;
import com.rappytv.widgets.gui.widgets.GuiWidget;
import com.rappytv.widgets.gui.widgets.GuiWidgetCategory;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class WidgetAddon extends LabyAddon<WidgetAddonConfiguration> {

    private static WidgetAddon instance;

    @Override
    protected void enable() {
        instance = this;

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
