package com.topoedits.listeners;

import com.topoedits.HG;
import com.topoedits.HGestado;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class MotdListener implements Listener {
    public static HG plugin;

    public MotdListener(HG instance) {
        plugin = instance;
    }

    @EventHandler
    public void motd(ServerListPingEvent e){
        if(HG.getEstado().equals(HGestado.ESPERANDO)) {
            e.setMotd(ChatColor.GREEN + "Esperando a m\u00e1s jugadores " + ChatColor.GRAY + "hg1.swordcraft.co");
        }
        if(HG.getEstado().equals(HGestado.EMPEZADA)){
            e.setMotd(ChatColor.RED + "Partida Empezada, prueba con: " + ChatColor.GRAY + "hg2.swordcraft.co");
        }
    }
}
