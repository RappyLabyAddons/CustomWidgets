package com.rappytv.widgets;

import com.rappytv.widgets.gui.activities.WidgetActivity;
import com.rappytv.widgets.gui.widgets.GuiWidgetProperties;
import net.labymod.api.addon.AddonConfig;
import net.labymod.api.client.gui.screen.activity.Activity;
import net.labymod.api.client.gui.screen.widget.widgets.activity.settings.ActivitySettingWidget.ActivitySetting;
import net.labymod.api.client.gui.screen.widget.widgets.input.SwitchWidget.SwitchSetting;
import net.labymod.api.configuration.loader.annotation.ConfigName;
import net.labymod.api.configuration.loader.property.ConfigProperty;
import net.labymod.api.util.MethodOrder;
import java.util.HashMap;

@ConfigName("settings")
public class WidgetAddonConfiguration extends AddonConfig {

    @SwitchSetting
    private final ConfigProperty<Boolean> enabled = new ConfigProperty<>(true);
    private final ConfigProperty<HashMap<Integer, GuiWidgetProperties>> widget = new ConfigProperty<>(new HashMap<>());

    @MethodOrder(after = "enabled")
    @ActivitySetting
    public Activity openWidgetActivity() {
        return new WidgetActivity(WidgetAddon.get());
    }

    @Override
    public ConfigProperty<Boolean> enabled() {
        return enabled;
    }
    public HashMap<Integer, GuiWidgetProperties> getWidgets() {
        return this.widget.get();
    }
}
