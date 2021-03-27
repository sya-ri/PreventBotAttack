package com.github.syari.bungee.PreventBotAttack;

import com.github.syari.bungee.PreventBotAttack.api.*;
import net.md_5.bungee.api.plugin.*;
import org.jetbrains.annotations.*;
import java.io.*;

public class Settings extends YamlConfig {
    public Settings() throws IOException {
        super("settings.yml");
    }

    private int connectionPerSecond;
    private boolean showDisconnectLog;
    private String onAddBlackListCommand;
    private String onRemoveBlackListCommand;
    private long commandTimeOut;

    @Override
    public void load() throws IOException {
        super.load();
        connectionPerSecond = config.getInt("limit.cps", 10);
        showDisconnectLog = config.getBoolean("debug.disconnect");
        onAddBlackListCommand = config.getString("command.blacklist.add");
        onRemoveBlackListCommand = config.getString("command.blacklist.remove");
        commandTimeOut = config.getLong("command.timeout");
    }

    public int getConnectionPerSecond() {
        return connectionPerSecond;
    }

    public boolean isShowDisconnectLog() {
        return showDisconnectLog;
    }

    public String getOnAddBlackListCommand() {
        return onAddBlackListCommand;
    }

    public String getOnRemoveBlackListCommand() {
        return onRemoveBlackListCommand;
    }

    public long getCommandTimeOut() {
        return commandTimeOut;
    }
}
