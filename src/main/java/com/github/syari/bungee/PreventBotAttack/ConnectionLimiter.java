package com.github.syari.bungee.PreventBotAttack;

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
}
