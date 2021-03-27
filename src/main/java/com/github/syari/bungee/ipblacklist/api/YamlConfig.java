package com.github.syari.bungee.ipblacklist.api;

import net.md_5.bungee.api.plugin.*;
import net.md_5.bungee.config.*;
import org.jetbrains.annotations.*;
import java.io.*;
import java.nio.file.*;

public class YamlConfig {
    private static final ConfigurationProvider provider = ConfigurationProvider.getProvider(YamlConfiguration.class);

    @NotNull protected final Plugin plugin;
    @NotNull protected final String fileName;
    @NotNull protected final File folder;
    @NotNull protected final File file;
    protected Configuration config;

    public YamlConfig(@NotNull Plugin plugin, @NotNull String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        folder = plugin.getDataFolder();
        file = new File(folder, fileName);
    }

    public void load() throws IOException {
        if (!folder.exists()) {
            if (!folder.mkdir()) throw new IOException("Fail folder::mkdir");
        }
        if (!file.exists()) {
            try (InputStream inputStream = plugin.getResourceAsStream(fileName)) {
                Files.copy(inputStream, file.toPath());
            }
        }
        config = provider.load(file);
    }

    public void save() throws IOException {
        provider.save(config, file);
    }
}
