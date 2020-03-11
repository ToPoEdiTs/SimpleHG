package com.topoedits.eventos;

import com.topoedits.HG;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class RomperEvento implements Listener {
    private HG plugin;

    public RomperEvento(HG instance){
        plugin = instance;
    }

    @EventHandler
    public void onRomper(BlockBreakEvent event){
        Player player = event.getPlayer();
        event.setCancelled(true);
        System.out.println("Intento de romper bloques");
    }
}