package fr.program.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class NightCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player plr = ((Player) sender).getPlayer();

            Bukkit.broadcastMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" + " La nuit tombe ! Prenez garde.");

            PotionEffect blindness = new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 3);

            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.getUniqueId() != plr.getUniqueId()) { // Si l'ID du joueur est différent de celui du MJ.
                    p.addPotionEffect(blindness);
                }
            }
            return true;
        }
        return false;
    }
}
