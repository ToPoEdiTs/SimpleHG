package com.topoedits.comandos;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class HG implements Listener {

    /**
     * Hay que hacer el CommandExecutor y ver si funciona con el HG ya puesto en el onCommand
     * o hay que ponerlo en la clase Principal con un onCommand("").
     */

    public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
        Player player = (Player) sender;

        if (cmd.getName().equalsIgnoreCase("HG")) {
            player.sendMessage(ChatColor.BLUE.toString() + ChatColor.BOLD + "HG Plugin informaci\u00D3n");
            player.sendMessage(ChatColor.DARK_GRAY + "Plugin creado por: (ToPoEdiTs) para mc.SwordCraft.co");
            player.sendMessage(ChatColor.DARK_GRAY + "Plugin version: 1.0");
            if (player.hasPermission("hg.admin")) {

                if (args.length == 0) {
                    player.sendMessage("");

                    return true;
                }

                if (args[0].equalsIgnoreCase("empezar")) {
                    player.sendMessage(ChatColor.GREEN + "Has puesto la partida en marcha.");

                    /**
                     * Importar la variable (startGameCountdown(player);)
                     * para que el juego se inicie con el comando.
                     */
                        //Añadir aqui la variable
                    return true;
                }
                if (args[0].equalsIgnoreCase("parar")) {
                    player.sendMessage(ChatColor.RED + "Has parado la partida.");
                    Bukkit.shutdown();
                    return true;
                }
            }
        }
        player.sendMessage(ChatColor.RED + "No tienes permiso para ejecutar esta linea de comandos.");
        return false;
    }
}
