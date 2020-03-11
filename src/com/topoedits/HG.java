package com.topoedits;

import com.topoedits.com.topoedits.api.TabTitleAPI;
import com.topoedits.eventos.MorirEvento;
import com.topoedits.eventos.RomperEvento;
import com.topoedits.listeners.KitsListener;
import com.topoedits.listeners.MotdListener;
import com.topoedits.com.topoedits.api.ActionBarAPI;
import com.topoedits.manager.ScoreBoardManager;

import org.black_ixx.playerpoints.PlayerPoints;
import org.black_ixx.playerpoints.PlayerPointsAPI;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class HG extends JavaPlugin implements Listener {

    private KitsListener seleccionarkit;
    public final MorirEvento mbl = new MorirEvento(this);
    //echar un vistazo a la linea de abajo ya que producia antes el error de que el plugin no funcionara (public static HG plugin)
   // public static HG plugin;

    public static HGestado estado;
    public static List<String> Players = new ArrayList<>();
    public static int Countdown;
    int tiempo = 30;

    public void onEnable() {

        getServer().getPluginManager().registerEvents(this, this);
        PluginManager pm = getServer().getPluginManager();
        setEstado(HGestado.ESPERANDO);
        seleccionarkit = new KitsListener(this);
        pm.registerEvents(new MotdListener(this), this);
        pm.registerEvents(new MorirEvento(this), this);
        pm.registerEvents(new MorirEvento(this), this);
        pm.registerEvents(new ScoreBoardManager(this), this);
        pm.registerEvents(new RomperEvento(this), this);
        pm.registerEvents(new KitsListener(this), this);
        pm.registerEvents(new ActionBarAPI(this), this);
        pm.registerEvents(new TabTitleAPI(this), this);

    }

    public static HGestado getEstado() {
        return estado;

    }

    public static void setEstado(HGestado estado) {
        HG.estado = estado;
    }

     @EventHandler
     public void playerJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
         player.setGameMode(GameMode.SURVIVAL);
         PlayerPointsAPI pp = ((PlayerPoints) Bukkit.getServer().getPluginManager().getPlugin("PlayerPoints")).getAPI();
     //  ActionBarAPI.sendActionBar(player, ChatColor.BLACK + "Puntos: " + ChatColor.WHITE + pp.look(player.getDisplayName()));
         event.setJoinMessage(ChatColor.AQUA + player.getDisplayName() + ChatColor.GREEN + " Se ha unido " + ChatColor.GRAY + "("
                 + Bukkit.getOnlinePlayers().length + "/" + Bukkit.getMaxPlayers() + ")");
         Location loc = new Location(getServer().getWorld("world"), -1408, 130, -245);
        // player.teleport(loc);
         player.setHealth(20);
         player.setFoodLevel(50);
         for(String current : Players){
             getServer().getPlayer(current).teleport(loc);
         }
    }

    public static ItemStack BRUJULA() {
        ItemStack item = new ItemStack(Material.COMPASS, 1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.AQUA.toString() + ChatColor.ITALIC + "Brujula");
        List<String> lore = new ArrayList<String>();
        item.setItemMeta(im);
        return item;

    }

    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        getServer().getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
            public void run() {
                p.getInventory().setItem(8, BRUJULA());
                p.getInventory().setItem(0, KitsListener.KITS());
                p.updateInventory();
            }
        }, 5L);
    }


    @EventHandler
    public void ArenaEmpezar(PlayerJoinEvent event){
        Player player = event.getPlayer();
    if(Bukkit.getServer().getOnlinePlayers().length >=3) {
        startGameCountdown(player);
        }
    }

    public void startGameCountdown(final Player player) {

          Countdown = this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
              public void run() {
                  // if (Bukkit.getServer().getOnlinePlayers().length >= 1) {
                  tiempo--;

                  if (tiempo == 30) {
                      Bukkit.broadcastMessage(ChatColor.GREEN + "Partida empezando en" + ChatColor.AQUA + " 30 " + ChatColor.GREEN + "segundos!");
                  }
                  if (tiempo == 20) {
                      Bukkit.broadcastMessage(ChatColor.GREEN + "Partida empezando en" + ChatColor.AQUA + " 20 " + ChatColor.GREEN + "segundos!");
                  }
                  if (tiempo == 15) {
                      Bukkit.broadcastMessage(ChatColor.GREEN + "Partida empezando en" + ChatColor.AQUA + " 15 " + ChatColor.GREEN + "segundos!");
                  }
                  if (tiempo == 10) {
                      Bukkit.broadcastMessage(ChatColor.GREEN + "Partida empezando en" + ChatColor.AQUA + " 10 " + ChatColor.GREEN + "segundos!");
                  }
                  if (tiempo == 5) {
                      Bukkit.broadcastMessage(ChatColor.GREEN + "Partida empezando en" + ChatColor.AQUA + " 5 " + ChatColor.GREEN + "segundos!");
                  }
                  if (tiempo == 4) {
                      Bukkit.broadcastMessage(ChatColor.GREEN + "Partida empezando en" + ChatColor.AQUA + " 4 " + ChatColor.GREEN + "segundos!");
                  }
                  if (tiempo == 3) {
                      Bukkit.broadcastMessage(ChatColor.GREEN + "Partida empezando en" + ChatColor.AQUA + " 3 " + ChatColor.GREEN + "segundos!");
                  }
                  if (tiempo == 2) {
                      Bukkit.broadcastMessage(ChatColor.GREEN + "Partida empezando en" + ChatColor.AQUA + " 2 " + ChatColor.GREEN + "segundos!");
                  }
                  if (tiempo == 1) {
                      Bukkit.broadcastMessage(ChatColor.GREEN + "Partida empezando en" + ChatColor.AQUA + " 1 " + ChatColor.GREEN + "segundos!");
                  }

               /*if (tiempo == 30 || tiempo == 15 || tiempo < 11) {
                      Bukkit.broadcastMessage(ChatColor.GREEN + "Partida empezando en " + ChatColor.AQUA + tiempo + ChatColor.GREEN + " segundos!");
                  }*/
                  if (tiempo == 0) {
                      setEstado(HGestado.EMPEZADA);
                      Bukkit.broadcastMessage(ChatColor.GREEN + "Partida empezada lucha para Sobrevivir!");
                      World mundo = player.getWorld();
                      player.setWhitelisted(true);
                      System.out.println("Whitelist puesta en ON");
                      player.setHealth(22);
                      player.setFoodLevel(22);
                      Location loc = new Location(getServer().getWorld("world"), -1393, 75, -227);
                      player.teleport(loc);

                      for (String current : Players) {
                          getServer().getPlayer(current).teleport(loc);
                          player.teleport(loc);
                      }
                  }

              }

          }, 0L, 20L);
    }

    public void onDisable() {
        Bukkit.getServer().setWhitelist(false);
        System.out.println("Whitelist puesta en OFF");
    }

}
