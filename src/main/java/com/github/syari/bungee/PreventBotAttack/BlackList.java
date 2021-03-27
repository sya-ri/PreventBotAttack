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

    private static final Settings settings = Main.getSettings();

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

    public void add(@NotNull String ip) {
        if (!list.contains(ip)) {
            list.add(ip);
            saveAfter100ms();
            String command = settings.getOnAddBlackListCommand();
            invokeCommandIfNotEmpty(command, ip);
        }
    }

    public void remove(@NotNull String ip) {
        if (list.remove(ip)) {
            saveAfter100ms();
            String command = settings.getOnRemoveBlackListCommand();
            invokeCommandIfNotEmpty(command, ip);
        }
    }

    public List<String> getList() {
        return Collections.unmodifiableList(list);
    }

    private @Nullable ScheduledTask task;

    private void saveAfter100ms() {
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
    }

    private void invokeCommandIfNotEmpty(@NotNull String command, @NotNull String ip) {
        if (!command.isEmpty()) {
            try {
                SystemCommand.invoke(command.replace("%ip%", ip), settings.getCommandTimeOut());
            } catch (InterruptedException | IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
