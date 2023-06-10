package fr.program.cmds;

import fr.program.Main;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PartieCMD implements CommandExecutor {

    private final Main plugin;
    public PartieCMD(Main main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        FileConfiguration configPlugin = plugin.getConfig();

        if (sender instanceof Player player) {
            if (args.length > 0) {
                String selection_mode = args[0];

                if (selection_mode.equals("start") || selection_mode.equals("commencer")) {
                    // Automatique
                    Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                            "La partie va commencer !"
                    );

                    new BukkitRunnable() {

                        int i = 3;
                        @Override
                        public void run() {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                            }
                            Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    "La partie commence dans " + ChatColor.GREEN + i
                            );
                            if (i == 0) {
                                this.cancel();
                            }
                            i--;
                        }
                    }.runTaskTimer(plugin, 0, 20*1); // 1s -> 20ticks

                    List<String> GameCurrent_Players = plugin.getConfig().getStringList("partie.joueurs");
                    String amount_of_wolf = configPlugin.getString("partie.nb_total_role.Loup Garou");
                    System.out.println("\n");
                    System.out.println(amount_of_wolf);
                    System.out.println("\n");
                    for (String p_name : GameCurrent_Players) {
                        Player p = Bukkit.getPlayer(p_name);
                        if (p != null) {
                            PotionEffect effect = new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 10); // 20ticks -> 1s, 5min -> 20*300
                            p.addPotionEffect(effect);
                        }
                    }

                    return true;
                } else if (selection_mode.equals("joueur") || selection_mode.equals("joueurs") || selection_mode.equals("player") || selection_mode.equals("players")) {
                    if (args.length > 3) {
                        String mode = args[1];
                        String possiblePlayer = args[2];

                        if (mode.equals("ajouter") || mode.equals("add")) {
                            if (managePlayer(possiblePlayer, "add")) {
                                return true;
                            }
                        } else if (mode.equals("retirer") || mode.equals("remove")) {
                            if (managePlayer(possiblePlayer, "remove")) {
                                return true;
                            }
                        } else if (mode.equals("liste") || mode.equals("list")) {
                            List<String> listes_players_game = configPlugin.getStringList("partie.joueurs");
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    "Voici la liste de tout les joueurs qui participent au jeu : "
                                    );
                            for (String name_p : listes_players_game) {
                                player.sendMessage("- " + ChatColor.YELLOW + Bukkit.getPlayer(name_p).getName() + ChatColor.WHITE);
                            }
                            return true;
                        }
                    }
                } else if (selection_mode.equals("reset") || selection_mode.equals("reinitialise")) {
                    reloadConfig(configPlugin);
                    player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                            "La partie a été réinitialisé, les paramètres ont aussi été remis par défaut !"
                    );
                    return true;
                }
            }
        }
        return false;
    }

    private boolean managePlayer(String plr_name, String mode) {
        Player target = Bukkit.getPlayer(plr_name);
        if (target != null) {
            List<String> currentList = plugin.getConfig().getStringList("partie.joueurs");
            if (mode.equals("add")) {
                currentList.add(target.getName());
                plugin.getConfig().set("partie.joueurs", currentList);
                plugin.saveConfig();
            } else if (mode.equals("remove")) {
                currentList.remove(target.getName());
                plugin.getConfig().set("partie.joueurs", currentList);
                plugin.saveConfig();
            }
        }
        return false;
    }

    private void reloadConfig(FileConfiguration config) {
        config.set("compos_jeu.roles", getDefaultRoles());
        config.set("compos_jeu.nb_roles", getDefaultRoleNumbers());
        config.set("partie.joueurs", getDefaultPlayers());
        config.set("partie.roles_players", null);
        config.set("partie.nb_total_role", null);
        plugin.saveConfig();
    }

    private List<String> getDefaultRoles() {
        return Arrays.asList(
                "Loup Garou",
                "Infect Père des loups",
                "Grand Méchant loup",
                "Villageois",
                "Cupidon",
                "Voyante",
                "Soeurs",
                "Petite fille",
                "Renard",
                "Chevalier à l'épée rouillée",
                "Ancien",
                "Sorcière",
                "Montreur d'ours",
                "Chasseur",
                "Enfant sauvage",
                "Chien Loup",
                "Loup Garou Blanc",
                "Joueur de flûte",
                "Ange"
        );
    }

    private List<Integer> getDefaultRoleNumbers() {
        return Arrays.asList(
                3,  // Loup Garou
                1,  // Infect Père des loups
                1,  // Grand Méchant loup
                7,  // Villageois
                1,  // Cupidon
                1,  // Voyante
                2,  // Sœurs
                1,  // Petite fille
                1,  // Renard
                1,  // Chevalier à l'épée rouillée
                1,  // Ancien
                1,  // Sorcière
                1,  // Montreur d'ours
                1,  // Chasseur
                1,  // Enfant sauvage
                1,  // Chien Loup
                1,  // Loup Garou Blanc
                1,  // Joueur de flûte
                1   // Ange
        );
    }

    private List<String> getDefaultPlayers() {
        return Arrays.asList();
    }
}
