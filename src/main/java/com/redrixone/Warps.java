package com.redrixone;

import com.redrixone.commands.SetWarp;
import com.redrixone.commands.Warp;
import com.redrixone.filemanager.WarpsFile;
import org.bukkit.plugin.java.JavaPlugin;

public class Warps extends JavaPlugin {

    private WarpsFile warpsFile;

    @Override
    public void onEnable() {
        super.onEnable();
        warpsFile = new WarpsFile(this);
        getCommand("setwarp").setExecutor(new SetWarp(warpsFile));
        getCommand("warp").setExecutor(new Warp(warpsFile));
        getLogger().info("Plugin enabled!");
    }

    @Override
    public void onDisable() {
        super.onDisable();
        warpsFile.saveWarpsConfig();
        getLogger().info("Plugin disabled!");
    }

}
