package fr.program.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class DayCMD implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player plr = ((Player) sender).getPlayer();

            Bukkit.broadcastMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" + " Le soleil se lève ! Réveillez-vous.");

            for (Player p : Bukkit.getOnlinePlayers()) {
                if (p.hasPotionEffect(PotionEffectType.BLINDNESS)) {
                    p.removePotionEffect(PotionEffectType.BLINDNESS);
                }
            }
            return true;
        }
        return false;
    }
}
