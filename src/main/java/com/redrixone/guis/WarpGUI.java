package com.redrixone.guis;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public class WarpGUI {
    //todo: Migliorie a livello di storage per aggiungere i warp nella gui tramite il gioco.
    public Inventory warpGUI() {
        Inventory inventory = Bukkit.createInventory(null, 27, "Warps");


        return inventory;
    }

}
