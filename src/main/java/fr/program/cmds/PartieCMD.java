package fr.program.cmds;

import fr.program.LoupGarouMain;
import it.unimi.dsi.fastutil.chars.CharBigArrays;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Objects;

public class PartieCMD implements CommandExecutor {

    private LoupGarouMain plugin;
    public PartieCMD(LoupGarouMain pl) {
        this.plugin = pl;
    }

    private void configRemoveRolePlayer(String possiblePlayer) {
        for (int i = 1; i < 4; i++) {
            if (possiblePlayer.equals(Objects.requireNonNull(plugin.getConfig().get("roles.loup-" + i)).toString())) {
                plugin.getConfig().set("roles.loup-"+i, "None");
                break;
            }
        }
        if (possiblePlayer.equals(Objects.requireNonNull(plugin.getConfig().get("roles.loup-pere")).toString())) {
            plugin.getConfig().set("roles.loup-pere", "None");
        } else if (possiblePlayer.equals(Objects.requireNonNull(plugin.getConfig().get("roles.mechant-loup")).toString())) {
            plugin.getConfig().set("roles.mechant-loup", "None");
        } else if (possiblePlayer.equals(Objects.requireNonNull(plugin.getConfig().get("roles.loup-blanc")).toString())) {
            plugin.getConfig().set("roles.loup-blanc", "None");
        } else if (possiblePlayer.equals(Objects.requireNonNull(plugin.getConfig().get("roles.voyante")).toString())) {
            plugin.getConfig().set("roles.voyante", "None");
        } else if (possiblePlayer.equals(Objects.requireNonNull(plugin.getConfig().get("roles.sorciere")).toString())) {
            plugin.getConfig().set("roles.sorciere", "None");
        } else if (possiblePlayer.equals(Objects.requireNonNull(plugin.getConfig().get("roles.ange")).toString())) {
            plugin.getConfig().set("roles.ange", "None");
        }

        for (int i = 1; i < 8; i++) {
            if (possiblePlayer.equals(Objects.requireNonNull(plugin.getConfig().get("roles.villageois-" + i)).toString())) {
                plugin.getConfig().set("roles.villageois-"+i, "None");
                break;
            }
        }
        plugin.saveConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player plr) {
            if (args.length > 0) {
                String p1 = args[0];

                if (p1.equalsIgnoreCase("reset")
                        || p1.equalsIgnoreCase("relancer")
                        || p1.equalsIgnoreCase("restart")
                        || p1.equalsIgnoreCase("red??marrer")
                        || p1.equalsIgnoreCase("redemarrer"))
                {
                    plr.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                            " Les donn??es sont entrain d'??tre supprim??es...");
                    for (int i = 1; i < 4; i++) {
                        plugin.getConfig().set("roles.loup-" + i, "None");
                    }

                    plugin.getConfig().set("roles.loup-pere", "None");
                    plugin.getConfig().set("roles.mechant-loup", "None");
                    plugin.getConfig().set("roles.loup-blanc", "None");
                    plugin.getConfig().set("roles.voyante", "None");
                    plugin.getConfig().set("roles.sorciere", "None");
                    plugin.getConfig().set("roles.ange", "None");

                    for (int i = 1; i < 8; i++) {
                        plugin.getConfig().set("roles.villageois-" + i, "None");
                    }

                    plugin.saveConfig();
                    plr.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                            " Les donn??es sont supprim??es.");
                    return true;
                } else if (p1.equalsIgnoreCase("start") || p1.equalsIgnoreCase("commencer")) {
                    Bukkit.broadcastMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                            " La partie va commenc?? ! ");

                    plr.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                            " MJ, vous devez utiliser la commande : "
                            + ChatColor.DARK_GREEN + "commencer " + ChatColor.GREEN + ChatColor.ITALIC + "role " + ChatColor.RESET + ChatColor.WHITE  + "o?? role peut ??tre : \n\n" +
                            ChatColor.YELLOW + "- " + ChatColor.WHITE + "loup \n" +
                            ChatColor.YELLOW + "- " + ChatColor.WHITE + "pere-loup \n" +
                            ChatColor.YELLOW + "- " + ChatColor.WHITE + "mechant-loup \n" +
                            ChatColor.YELLOW + "- " + ChatColor.WHITE + "loup-blanc \n" +
                            ChatColor.YELLOW + "- " + ChatColor.WHITE + "voyante \n" +
                            ChatColor.YELLOW + "- " + ChatColor.WHITE + "sorciere \n" +
                            ChatColor.YELLOW + "- " + ChatColor.WHITE + "ange \n" +
                            ChatColor.WHITE + "\n\n Ils doivent ??tre dans cette ordre. 15 secondes seront donn??s, une fois que se sera fait, ils redormiront."
                    );
                    return true;
                } else {
                    if (args.length > 2) {
                        String possiblePlayer = args[1];

                        if (Bukkit.getPlayer(possiblePlayer) == null) {
                            plr.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    " Le joueur n'est pas connect??.");
                            return false;
                        }

                        String roleManagement = args[2];
                        if (args.length > 3) {
                            String nextRole = args[3];
                            if (p1.equals("role") && Objects.equals(roleManagement, "set")) {
                                if (plugin.getConfig().contains("roles." + nextRole)) {
                                    configRemoveRolePlayer(possiblePlayer);
                                    plugin.getConfig().set("roles." + nextRole, possiblePlayer);
                                    plugin.saveConfig();
                                    plr.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                            " Le joueur " + possiblePlayer + " aura le r??le " + nextRole + ".");
                                    return true;
                                } else {
                                    plr.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                            " Veuillez choisir un role qui existe: 'loup-' + num??ro de 1 ?? 4, 'villageois-' + num??ro de 1 ?? 7, 'loup-pere', 'mechant-loup', 'loup-blanc', 'voyante', 'sorciere' ou 'ange'.");
                                    return false;
                                }
                            }
                        } else {
                            if (p1.equals("role") && Objects.equals(roleManagement, "retirer")) {
                                plr.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                        " Recherche du joueur en cours...");

                                configRemoveRolePlayer(possiblePlayer);

                                plr.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                        " Joueur retir?? !");
                                return true;
                            } else {
                                plr.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                        " Il existe que 'set' et 'retirer' comme param??tre a placer en troisi??me argument.");
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
