package com.github.syari.bungee.PreventBotAttack;

import com.github.syari.bungee.PreventBotAttack.api.*;
import org.jetbrains.annotations.*;
import java.util.*;

public class BlackList extends YamlConfig {
    public BlackList() {
        super("blacklist.yml");
    }

    private final Set<String> list = new HashSet<>();

    public boolean contains(@NotNull String ip) {
        return list.contains(ip);
    }

    public void add(@NotNull String ip) {
        list.add(ip);
    }
}
