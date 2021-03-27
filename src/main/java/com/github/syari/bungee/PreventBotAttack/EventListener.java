package com.github.syari.bungee.PreventBotAttack;

import net.md_5.bungee.api.connection.*;
import net.md_5.bungee.api.event.*;
import net.md_5.bungee.api.plugin.*;
import net.md_5.bungee.event.*;

public class EventListener implements Listener {
    @EventHandler
    public void on(PlayerHandshakeEvent event) {
        PendingConnection connection = event.getConnection();
        String address = connection.getSocketAddress().toString();
        String ip = address.substring(1, address.indexOf(':'));
        if (Main.getConnectionLimiter().checkLimit(ip)) {
            connection.disconnect();
            if (Main.getSettings().isShowDisconnectLog()) {
                System.out.println("[" + address + "] <-> Disconnected by ConnectionLimiter");
            }
        }
    }
}
