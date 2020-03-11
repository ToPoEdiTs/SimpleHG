package com.topoedits.eventos;

import com.topoedits.HG;

import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class MorirEvento implements Listener {

    public static HG plugin;

    public MorirEvento(HG instance) {
        plugin = instance;
    }

    @EventHandler
    public void onMorir(PlayerDeathEvent event) {
        String muerto = event.getEntity().getName();
        String matador = event.getEntity().getKiller().getName();
        event.setDeathMessage(ChatColor.GREEN.toString() + matador + ChatColor.AQUA + " ha matado a " + ChatColor.RED + muerto);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        if (event instanceof PlayerDeathEvent) {
            Player player = (Player) event.getEntity();
            player.getInventory().clear();
            player.updateInventory();
            PlayerPointsAPI pp = ((PlayerPoints) Bukkit.getServer().getPluginManager().getPlugin("PlayerPoints")).getAPI();
            player.kickPlayer("Has muerto, Puntos: " + pp.look(player.getDisplayName()));
        }

    }

}