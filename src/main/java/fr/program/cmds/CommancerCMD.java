package fr.program.cmds;

import fr.program.LoupGarouMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class CommancerCMD implements CommandExecutor {

    LoupGarouMain plugin;
    public CommancerCMD(LoupGarouMain pl) {
        this.plugin = pl;
    }

    private void GiveTime(Player plr) {
        plr.removePotionEffect(PotionEffectType.BLINDNESS);
        plr.setLevel(16);
        Bukkit.getScheduler().runTaskTimer(plugin, (task) -> {
            if (plr.getLevel() > 0) {
                plr.setLevel(plr.getLevel() - 1);
            }

            if (plr.getLevel() == 0) {
                plr.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 3));
                task.cancel();
            }
        }, 0L, 20L);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player plr) {
            if (args.length > 0) {
                String possibleRole = args[0];
                if (possibleRole.equalsIgnoreCase("loup") || possibleRole.equalsIgnoreCase("loups")) {
                    for (int i = 1; i < 4; i++) {
                        String config_p = plugin.getConfig().getString("roles.loup-"+i);
                        if (config_p != null && !config_p.equals("None")) {
                            Player loup_p = Bukkit.getPlayer(config_p);
                            if (loup_p != null) {GiveTime(loup_p);}
                        }
                    }
                    return true;
                } else {
                    plr.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                            " Merci de préciser un argument valide: " +
                                    ChatColor.ITALIC + "loup" + ChatColor.RESET + ", " +
                                    ChatColor.ITALIC + "pere-loup" + ChatColor.RESET + ", " +
                                    ChatColor.ITALIC + "mechant-loup" + ChatColor.RESET + ", " +
                                    ChatColor.ITALIC + "loup-blanc" + ChatColor.RESET + ", " +
                                    ChatColor.ITALIC + "voyante" + ChatColor.RESET + ", " +
                                    ChatColor.ITALIC + "sorciere" + ChatColor.RESET + ", " +
                                    ChatColor.ITALIC + "ange" + ChatColor.RESET + ", "
                            );
                    return false;
                }
            } else {
                plr.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                        " Merci de préciser le rôle du joueur / du camp.");
                return false;
            }
        }
        return false;
    }
}
