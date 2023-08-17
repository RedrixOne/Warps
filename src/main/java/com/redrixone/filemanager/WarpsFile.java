package com.redrixone.filemanager;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class WarpsFile {

    private FileConfiguration warpsConfig;
    private File warpsFile;
    private JavaPlugin plugin;

    public WarpsFile(JavaPlugin plugin) {
        this.plugin = plugin;
        warpsFile = new File(plugin.getDataFolder(), "warps.yml");
        if (!warpsFile.exists()) {
            plugin.saveResource("warps.yml", false);
        }
        warpsConfig = YamlConfiguration.loadConfiguration(warpsFile);
    }

    public void saveWarpsConfig() {
        try {
            warpsConfig.save(warpsFile);
            reloadWarpsConfig();
        } catch (IOException e) {
            plugin.getLogger().warning("Impossibile salvare il file warps.yml: " + e.getMessage());
        }
    }

    private void reloadWarpsConfig() {
        warpsConfig = YamlConfiguration.loadConfiguration(warpsFile);
    }

    public FileConfiguration getWarpsConfig() {
        return warpsConfig;
    }
}
