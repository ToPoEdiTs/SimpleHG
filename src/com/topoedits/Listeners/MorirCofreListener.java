package com.topoedits.listeners;

import com.topoedits.HG;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class MorirCofreListener implements Listener {

    private HG plugin;

    public MorirCofreListener(HG instance){
        plugin = instance;
    }


    @EventHandler
    public void playerDied(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Location loc = player.getLocation();
        Block block = loc.getBlock().getRelative(BlockFace.DOWN); //the block below the player
        Block signBlock = loc.getBlock().getRelative(BlockFace.EAST);
        List<ItemStack> drops = event.getDrops(); //all of the items the player will drop upon death

        block.setType(Material.CHEST); //añadir un death cofre
        Chest chest = (Chest)block.getState();

        signBlock.setType(Material.SIGN_POST); //añadir un death cartel
        Sign deathSign = (Sign)signBlock.getState();
        deathSign.setLine(1, "R.I.P");
        deathSign.setLine(2, player.getName());
        deathSign.update();

        for (ItemStack i : drops) {
            if (i != null) {
                chest.getInventory().addItem(i); //añadir un inventario al cofre
            }
            if (chest.getInventory().firstEmpty() == -1) { //if el inventario esta lleno hace: lo de abajo
                block.getRelative(BlockFace.WEST).setType(Material.CHEST); //crear otro cofre
                chest = (Chest)block.getRelative(BlockFace.WEST).getState();
            }
        }

        for (ItemStack i : drops) {
            i.setTypeId(0); //eliminar los items dropeados
        }
    }

}
