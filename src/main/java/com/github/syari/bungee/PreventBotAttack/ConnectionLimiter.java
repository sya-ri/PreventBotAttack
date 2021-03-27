package com.github.syari.bungee.PreventBotAttack;

import net.md_5.bungee.api.plugin.*;
import org.jetbrains.annotations.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class ConnectionLimiter {
    private final Map<String, AtomicInteger> connections = new ConcurrentHashMap<>();

    public boolean checkLimit(@NotNull String ip) {
        int cps = Main.getSettings().getConnectionPerSecond();
        AtomicInteger counter;
        if (!connections.containsKey(ip)) {
            counter = new AtomicInteger();
            connections.put(ip, counter);
        } else {
            counter = connections.get(ip);
        }
        return cps < counter.incrementAndGet();
    }

    public void init() {
        Plugin plugin = Main.getInstance();
        plugin.getProxy().getScheduler().schedule(plugin, connections::clear, 0, 1, TimeUnit.SECONDS);
    }
}
