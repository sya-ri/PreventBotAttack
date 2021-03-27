package com.github.syari.bungee.PreventBotAttack.api;

import com.github.syari.bungee.PreventBotAttack.*;
import net.md_5.bungee.api.plugin.*;
import org.jetbrains.annotations.*;
import java.io.*;
import java.util.concurrent.*;

public class SystemCommand {
    private static final boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
    private static final File directory = Main.getInstance().getProxy().getPluginsFolder().getParentFile();

    public static void invoke(@NotNull String command, long timeOutMillis) {
        Plugin plugin = Main.getInstance();
        plugin.getProxy().getScheduler().runAsync(plugin, () -> {
            try {
                ProcessBuilder builder = new ProcessBuilder();
                if (isWindows) {
                    builder.command("cmd.exe", "/c", command);
                } else {
                    builder.command("sh", "-c", command);
                }
                builder.directory(directory);
                Process process = builder.start();
                process.waitFor(timeOutMillis, TimeUnit.MILLISECONDS);
            } catch (IOException | InterruptedException ex) {
                ex.printStackTrace();
            }
        });
    }
}
