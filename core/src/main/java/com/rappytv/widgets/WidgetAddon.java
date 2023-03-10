package com.rappytv.widgets;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class WidgetAddon extends LabyAddon<WidgetAddonConfiguration> {

    private static WidgetAddon instance;

    @Override
    protected void enable() {
        instance = this;

        registerSettingCategory();
    }

    public static WidgetAddon get() {
        return instance;
    }

    @Override
    protected Class<? extends WidgetAddonConfiguration> configurationClass() {
        return WidgetAddonConfiguration.class;
    }
}
