package com.github.syari.bungee.PreventBotAttack;

import com.github.syari.bungee.PreventBotAttack.api.*;
import net.md_5.bungee.api.plugin.*;
import java.io.*;

public class Settings extends YamlConfig {
    public Settings(Plugin plugin) throws IOException {
        super(plugin, "settings.yml");
    }

    private int connectionPerSecond;
    private boolean showDisconnectLog;

    @Override
    public void load() throws IOException {
        super.load();
        connectionPerSecond = config.getInt("limit.cps", 10);
        showDisconnectLog = config.getBoolean("debug.disconnect", false);
    }

    public int getConnectionPerSecond() {
        return connectionPerSecond;
    }

    public boolean isShowDisconnectLog() {
        return showDisconnectLog;
    }
}
