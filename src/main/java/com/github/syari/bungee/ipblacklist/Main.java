package com.github.syari.bungee.ipblacklist;

import net.md_5.bungee.api.plugin.*;
import java.io.*;

public class Main extends Plugin {
    private static Settings settings;
    private static ConnectionLimiter connectionLimiter;

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerListener(this, new EventListener());
        try {
            settings = new Settings(this);
            settings.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        connectionLimiter = new ConnectionLimiter();
    }

    public static Settings getSettings() {
        return settings;
    }

    public static ConnectionLimiter getConnectionLimiter() {
        return connectionLimiter;
    }
}
