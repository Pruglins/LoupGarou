package fr.program.cmds;

import fr.program.LoupGarouMain;
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
                        || p1.equalsIgnoreCase("redémarrer")
                        || p1.equalsIgnoreCase("redemarrer"))
                {
                    plr.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                            " Les données sont entrain d'être supprimées...");
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
                            " Les données sont supprimées.");
                    return true;
                } else {
                    if (args.length > 2) {
                        String possiblePlayer = args[1];

                        if (Bukkit.getPlayer(possiblePlayer) == null) {
                            Bukkit.broadcastMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    " Le joueur n'est pas connecté.");
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
                                            " Le joueur " + possiblePlayer + " aura le rôle " + nextRole + ".");
                                    return true;
                                } else {
                                    plr.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                            " Veuillez choisir un role qui existe: 'loup-' + numéro de 1 à 4, 'villageois-' + numéro de 1 à 7, 'loup-pere', 'mechant-loup', 'loup-blanc', 'voyante', 'sorciere' ou 'ange'.");
                                    return false;
                                }
                            }
                        } else {
                            if (p1.equals("role") && Objects.equals(roleManagement, "retirer")) {
                                plr.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                        " Recherche du joueur en cours...");

                                configRemoveRolePlayer(possiblePlayer);

                                plr.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                        " Joueur retiré !");
                                return true;
                            } else {
                                plr.sendMessage(ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                        " Il existe que 'set' et 'retirer' comme paramètre a placer en troisième argument.");
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
