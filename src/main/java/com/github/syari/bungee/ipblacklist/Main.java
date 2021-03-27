package com.github.syari.bungee.ipblacklist;

import net.md_5.bungee.api.plugin.*;

public class Main extends Plugin {
    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerListener(this, new EventListener());
    }
}
