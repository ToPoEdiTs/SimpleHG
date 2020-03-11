package com.topoedits.manager;

import com.topoedits.HG;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

public class ScoreBoardManager implements Listener {

    private HG plugin;
    private String Mapa = "Castillos";
    int scorebd;

    public ScoreBoardManager(HG hg){
        this.plugin = hg;
    }

    @EventHandler
    public void alEntrar(PlayerJoinEvent event){
        final Player p = event.getPlayer();
        this.updateScoreboard(p);
        if(!Bukkit.getScheduler().isCurrentlyRunning(scorebd)){
            scorebd = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {
                @Override
                public void run() {
                    updateScoreboard(p);
                }
            }, 20, 20);
        }
    }

    public void updateScoreboard(Player p){
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective obj = scoreboard.registerNewObjective("aaa", "bbb");
        obj.setDisplayName(ChatColor.GOLD + "Mapa: " + ChatColor.GREEN + "Castillos");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        obj.getScore(Bukkit.getOfflinePlayer(ChatColor.DARK_PURPLE + "Jugadores: ")).setScore(Bukkit.getOnlinePlayers().length);
            p.setScoreboard(scoreboard);
    }

}

