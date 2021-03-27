package com.github.syari.bungee.ipblacklist;

import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.*;
import net.md_5.bungee.event.*;

public class EventListener implements Listener {
    @EventHandler
    public void on(PlayerHandshakeEvent event) {
        String socketAddress = event.getConnection().getSocketAddress().toString();
        String ipAddress = socketAddress.substring(1, socketAddress.indexOf(':'));
        System.out.println("IP: " + ipAddress);
    }
}
