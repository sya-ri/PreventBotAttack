package com.github.syari.bungee.PreventBotAttack;

import net.md_5.bungee.api.plugin.*;
import java.io.*;

public class Main extends Plugin {
    private static Plugin instance;
    private static Settings settings;
    private static ConnectionLimiter connectionLimiter;
    private static BlackList blackList;

    @Override
    public void onEnable() {
        instance = this;
        getProxy().getPluginManager().registerListener(this, new EventListener());
        try {
            settings = new Settings();
            settings.load();
            blackList = new BlackList();
            blackList.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        connectionLimiter = new ConnectionLimiter();
        connectionLimiter.init();
    }

    public static Plugin getInstance() {
        return instance;
    }

    public static Settings getSettings() {
        return settings;
    }

    public static ConnectionLimiter getConnectionLimiter() {
        return connectionLimiter;
    }

    public static BlackList getBlackList() {
        return blackList;
    }
}
