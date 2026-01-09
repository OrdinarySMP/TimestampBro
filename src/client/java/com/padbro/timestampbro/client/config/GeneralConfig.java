package com.padbro.timestampbro.client.config;

import me.shedaniel.autoconfig.annotation.ConfigEntry;
import net.minecraft.ChatFormatting;

public class GeneralConfig {
    @ConfigEntry.Gui.Excluded
    public int configVersion = 0;

    @ConfigEntry.Gui.Tooltip
    public boolean enable = true;

    @ConfigEntry.Gui.Tooltip
    public String format = "[HH:mm:ss]";

    @ConfigEntry.ColorPicker
    @ConfigEntry.Gui.Tooltip
    public int textColor = 11184810;

    @ConfigEntry.Gui.Tooltip
    public String hoverText = "⏰";

    @ConfigEntry.ColorPicker
    @ConfigEntry.Gui.Tooltip
    public int hoverColor = 11184810;// gray
}
