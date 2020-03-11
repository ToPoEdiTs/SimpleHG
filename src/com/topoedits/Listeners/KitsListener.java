package com.topoedits.listeners;

import com.topoedits.HG;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class KitsListener implements Listener{

    private HG plugin;

    public KitsListener(HG hg) {
        this.plugin = hg;
    }

    public static ItemStack Scout = new ItemStack(Material.POTATO_ITEM);

    {
        ItemMeta itemmeta = Scout.getItemMeta();
        ArrayList<String> im = new ArrayList<String>();
        itemmeta.setDisplayName(ChatColor.GREEN + "Scout");
        im.add(ChatColor.GRAY + "Este kit es default, todos los pueden usar!");
        im.add(ChatColor.GRAY + "Contiene: Una espada de pierda.");
        itemmeta.setLore(im);
        Scout.setItemMeta(itemmeta);
    }

    public static ItemStack Tanque = new ItemStack(Material.IRON_HELMET);

    {
        ItemMeta itemmeta = Tanque.getItemMeta();
        ArrayList<String> im = new ArrayList<String>();
        itemmeta.setDisplayName(ChatColor.AQUA + "Tanque");
        im.add(ChatColor.GRAY + "Eres el tanque de la partida");
        im.add(ChatColor.GRAY + "Con items de hierro!");
        itemmeta.setLore(im);
        Tanque.setItemMeta(itemmeta);
    }

    public static Inventory inv = Bukkit.createInventory(null, 18, ChatColor.BLACK + "Kit Selector");

    {
        inv.setItem(0,Scout);
        inv.setItem(1,Tanque);
    }

    @EventHandler
    public void onInventoryThing(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getCurrentItem() == null && e.getCurrentItem().hasItemMeta() )return;
        Player player = (Player) e.getWhoClicked();
        if (!e.getInventory().getName().equalsIgnoreCase(inv.getName())) return;
        if (e.getCurrentItem().getItemMeta() == null) return;
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Scout")) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.DARK_GRAY + "Has elegido el kit:" + ChatColor.RED + " Scout!");
            //comando/dar items
            e.getWhoClicked().closeInventory();
        }
        if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Tanque")) {
            e.setCancelled(true);
            p.sendMessage(ChatColor.DARK_GRAY + "Has elegido el kit:" + ChatColor.RED + " Tanque!");
            //comando/dar items
            e.getWhoClicked().closeInventory();
        }

    }


    public static ItemStack KITS(){
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK, 1);
        ItemMeta im = item.getItemMeta();
        im.setDisplayName(ChatColor.GREEN + "Kit Selector");
        List<String> lore = new ArrayList<String>();
        lore.add(ChatColor.DARK_GRAY + "Selecciona un kit!");
        im.setLore(lore);
        item.setItemMeta(im);
        return item;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if(event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
            Player player = event.getPlayer();
            if(player.getItemInHand() != null) {
                ItemStack iteminhand = player.getItemInHand();
                if(iteminhand.hasItemMeta()){
                    if(iteminhand.getItemMeta().hasDisplayName() && iteminhand.getItemMeta().hasLore()){
                        if(iteminhand.equals(KITS())) {
                            System.out.println("Abriendo Menu [Correctamente]");
                            player.openInventory(inv);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onInvStuff(InventoryClickEvent e) {
        if(e.getAction().equals(InventoryAction.DROP_ALL_SLOT)) {
            e.setCancelled(true);
        }
        if(e.getAction().equals(InventoryAction.DROP_ONE_SLOT)) {
            e.setCancelled(true);
        }
    }


   /* @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        Material mat = p.getItemInHand().getType();

        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {


            System.out.println("Abriendo Menu");
            if (mat == Material.ENCHANTED_BOOK) {
                p.openInventory(inv);
            }
        }
    }*/
}
