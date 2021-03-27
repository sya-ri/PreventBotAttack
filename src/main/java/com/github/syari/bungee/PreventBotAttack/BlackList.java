package com.github.syari.bungee.PreventBotAttack;

import com.github.syari.bungee.PreventBotAttack.api.*;
import net.md_5.bungee.api.plugin.*;
import net.md_5.bungee.api.scheduler.*;
import org.jetbrains.annotations.*;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;

public class BlackList extends YamlConfig {
    public BlackList() {
        super("blacklist.yml");
    }

    private List<String> list;

    @Override
    public void load() throws IOException {
        super.load();
        list = config.getStringList("list");
    }

    @Override
    public void save() throws IOException {
        config.set("list", list);
        super.save();
    }

    public boolean contains(@NotNull String ip) {
        return list.contains(ip);
    }

    private @Nullable ScheduledTask task;

    public void add(@NotNull String ip) {
        list.add(ip);
        if (task == null) {
            Plugin plugin = Main.getInstance();
            task = plugin.getProxy().getScheduler().schedule(plugin, () -> {
                task = null;
                try {
                    save();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }, 100, TimeUnit.MILLISECONDS);
        }
        Settings settings = Main.getSettings();
        String command = settings.getOnAddBlackListCommand();
        if (!command.isEmpty()) {
            try {
                SystemCommand.invoke(command.replace("%ip%", ip), settings.getCommandTimeOut());
            } catch (InterruptedException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
