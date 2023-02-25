package fr.program.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class NightCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player plr = ((Player) sender).getPlayer();

            Bukkit.broadcastMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" + " La nuit tombe ! Prenez garde.");
            return true;
        }
        return false;
    }
}
