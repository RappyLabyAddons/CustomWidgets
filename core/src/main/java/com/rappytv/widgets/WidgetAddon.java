package com.rappytv.widgets;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class WidgetAddon extends LabyAddon<WidgetAddonConfiguration> {

    @Override
    protected void enable() {
        registerSettingCategory();
    }

    @Override
    protected Class<? extends WidgetAddonConfiguration> configurationClass() {
        return WidgetAddonConfiguration.class;
    }
}
