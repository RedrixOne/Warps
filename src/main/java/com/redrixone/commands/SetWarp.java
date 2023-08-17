package com.redrixone.commands;

import com.redrixone.filemanager.WarpsFile;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetWarp implements CommandExecutor {

    private WarpsFile warpsFile;

    public SetWarp(WarpsFile warpsFile) {
        this.warpsFile = warpsFile;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cComando eseguibile solo da un &4utente&c!"));
            return true;
        }

        if (args.length < 1) {
            sender.sendMessage(ChatColor.RED + "Specifica il nome del warp!");
            return true;
        }

        Player player = (Player) sender;
        String warpName = args[0];
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY();
        int z = player.getLocation().getBlockZ();
        float yaw = player.getLocation().getYaw();
        float pitch = player.getLocation().getPitch();
        String world = String.valueOf(player.getWorld().getName());
        ConfigurationSection warpSection = warpsFile.getWarpsConfig().getConfigurationSection(warpName.toLowerCase());

        if (warpSection != null) {
            sender.sendMessage(ChatColor.RED + "Warp già esistente!");
            return true;
        }

        FileConfiguration config = warpsFile.getWarpsConfig();
        config.set(warpName.toLowerCase() + ".world", world);
        config.set(warpName.toLowerCase() + ".X", x);
        config.set(warpName.toLowerCase() + ".Y", y);
        config.set(warpName.toLowerCase() + ".Z", z);
        config.set(warpName.toLowerCase() + ".Yaw", yaw);
        config.set(warpName.toLowerCase() + ".Pitch", pitch);
        warpsFile.saveWarpsConfig();
        sender.sendMessage(ChatColor.GREEN + "Warp creato con successo!");

        return true;
    }

}
