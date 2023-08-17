package com.redrixone.commands;

import com.redrixone.filemanager.WarpsFile;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class Warp implements CommandExecutor {

    private WarpsFile warpsFile;

    public Warp(WarpsFile warpsFile) {
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
        String warpName = args[0].toLowerCase();
        ConfigurationSection warpSection = warpsFile.getWarpsConfig().getConfigurationSection(warpName);
        int x = warpSection.getInt("X");
        int y = warpSection.getInt("Y");
        int z = warpSection.getInt("Z");
        float yaw = Float.parseFloat(warpSection.getString("Yaw"));
        float pitch = Float.parseFloat(warpSection.getString("Pitch"));
        String world = warpSection.getString("world");

        if (warpSection == null) {
            sender.sendMessage(ChatColor.RED + "Warp non esistente!");
            return true;
        }

        Location warp = new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
        player.teleport(warp);
        sender.sendMessage(ChatColor.GREEN + "Teletrasporto avvenuto con successo!");

        return true;
    }
}
