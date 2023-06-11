package fr.program.cmds;

import fr.program.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RolesCMD implements CommandExecutor {
    private final Main plugin;
    public RolesCMD(Main main) {
        this.plugin = main;
    }

    private String returnRandomRole(List<String> list) {
        Random random = new Random();
        return list.get(random.nextInt(list.size()));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        FileConfiguration configPlugin = plugin.getConfig();

        List<String> LoupGarouPlugin_Roles = configPlugin.getStringList("compos_jeu.roles");
        List<Integer> old_amount_per_roles = configPlugin.getIntegerList("compos_jeu.nb_roles");

        if (sender instanceof Player player) {
            if (args.length >= 1) {
                String selection_mode = args[0];
                if (selection_mode.equals("info")) {
                    String role = args[1];
                    switch (role) {
                        case "Loup-Garou":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                                    "Se réveille chaque nuit avec son groupe pour éliminer un membre du village qui ne fait pas parti de sa meute. Le joueur voté meurt au lever du jour"
                            );
                            break;
                        case "Infect-Père-des-Loups":
                        case "Infect-Père-Loups":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                                    "Il dévore un membre du village avec ses semblables mais s'il le souhaite il peut décider pour une nuit seulement de rendre la personne voté infecté qui devient un loup garou."
                            );
                            break;
                        case "Grand-Méchant-Loup":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                                    "Il dévore un membre du village avec ses semblables mais peut se réveiller une deuxième fois si aucun Loup-Garou, Enfant-Sauvage ou Chien-Loup n'est mort."
                            );
                            break;
                        case "Villageois":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                                    "Aucun réel impact dans la partie, il participe seulement au vote pour éliminer un joueur de la partie."
                            );
                            break;
                        case "Cupidon":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                                    "Se réveille la première nuit pour rendre amoureux deux joueurs, peu import leur rôle."
                            );
                            break;
                        case "Voyante":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                                    "Peut regarder le rôle d'un joueur de son choix, une fois par nuit."
                            );
                            break;
                        case "2-Soeurs":
                        case "Deux-Soeurs":
                        case "Soeurs":
                        case "Soeur":
                        case "Sœurs":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                                    "Se réveillent chaque nuit pour discuter silencieusement des décisions à prendre si elles le souhaitent, ou peuvent tout simplement se voir et dormir directement.."
                            );
                            break;
                        case "Petite-Fille":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                                    "Ouvrir les yeux un peu de temps en temps pour entrevoir des actions des joueurs pendant la nuit. Elle n'a pas le droit de se faire passer pour un loup garou."
                            );
                            break;
                        case "Renard":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                                    "Désigne un groupe de 3 personnes et sait si un loup garou s'y trouve, sans savoir qui c'est réellement."
                            );
                            break;
                        case "Chevalier-Epée-Rouillée":
                        case "Chevalier-Epee-Rouillee":
                        case "Chevalier-Épée-Rouillée":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                                    "S'il meurt, le premier loup garou qui est a sa gauche meurt suite au blessure de l'épée qui l'a empoisonné."
                            );
                            break;
                        case "Ancien":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                                    "Il a une seconde vie lorsqu'il se fait dévorer par un/des Loups-Garou. Si le village décide de le tuer pendant la nuit (par exemple la Sorcière) ou lors d'un vote, ils n'ont plus accès aux pouvoirs."
                            );
                            break;
                        case "Sorcière":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                                    "Elle peut donner une potion qui fait survivre une victime des loups garou ou peut tuer quelqu'un de son choix, ou rien faire."
                            );
                            break;
                        case "Montreur-Ours":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                                    "Chaque matin, s'il y a un loup garou voisin de ce joueur, il est mis au courant."
                            );
                            break;
                        case "Chasseur":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                                    "Lors de sa mort provoqué par les Loups-Garou ou le village, il peut décider d'éliminer quelqu'un."
                            );
                            break;
                        case "Enfant-Sauvage":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                                    "Désigne un joueur modèle la première nuit, si ce dernier meurt, l'enfant sauvage devient loup garou pour les prochaines nuits."
                            );
                            break;
                        case "Chien-Loup":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                                    "Décide s'il souhaite faire parti des loups ou des membres du village la première nuit."
                            );
                            break;
                        case "Loup-Garou-Blanc":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                                    "Il élimine les membres du village avec ces confrères mais une nuit sur deux il doit voter un loup garou, son but est de rester le dernier survivant de la partie."
                            );
                            break;
                        case "Joueur-Flûte":
                        case "Flûte":
                        case "Joueur-Flute":
                        case "Flute":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                                    "Choisi 2 joueurs chaque nuit et s'il arrive à charmer tout les les joueurs il gagne la partie."
                            );
                            break;
                        case "Ange":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                                    "S'il est éliminé lors du premier tour, après la première nuit : il gagne la partie !"
                            );
                            break;

                        default:
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                                    "Ce rôle est introuvable ! S'il y a pas un simple mot, merci de mettre un '-' entre chaque mot."
                            );
                            break;
                    }
                }
                else if (selection_mode.equals("set")) {
                    Player p = Bukkit.getPlayer(args[1]);

                    if (p!=null && args[2] != null) {
                        int index = Integer.parseInt(args[2]);
                        if (index >= 0 && index <= 19) {
                            if (index == 0) {
                                configPlugin.set("partie.roles_players." + p.getName(), null);
                                plugin.saveConfig();
                            } else {
                                configPlugin.set("partie.roles_players." + p.getName(), index);
                                plugin.saveConfig();
                            }
                        } else {
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    "Il n'y a pas plus de 19 rôles !"
                            );
                        }
                    } else {
                        player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                "Merci de remplir les arguments : " + ChatColor.RED + "/set" + ChatColor.DARK_GREEN + "[player] " + "[index du rôle]" + ChatColor.WHITE + "\n" +
                                "Exemple: " + ChatColor.YELLOW + "/set Program132 1" + ChatColor.WHITE + " où 1 est le premier rôle ici, Loup-Garou." + "\n" +
                                "Pour savoir \"l'indexe du rôle\" vous pouvez regarder la documentation du plugin : " + ChatColor.DARK_BLUE + "github.com/Pruglins/LoupGarou"
                        );
                    }
                } else if (selection_mode.equals("random")) {
                    List<Integer> list_to_edit = old_amount_per_roles;
                    List<String> current_amount_player =  configPlugin.getStringList("partie.joueurs");
                    for (Player t : Bukkit.getOnlinePlayers()) {
                        String role_t = returnRandomRole(LoupGarouPlugin_Roles);
                        int index_role_t = LoupGarouPlugin_Roles.indexOf(role_t);
                        int current_amount_role = list_to_edit.get(index_role_t);

                        if (index_role_t != -1 && current_amount_role != -1) {
                            if (current_amount_role > 0) {
                                list_to_edit.set(index_role_t, current_amount_role - 1);
                                current_amount_player.add(t.getName());
                                configPlugin.set("partie.joueurs", current_amount_player);
                                configPlugin.set("compos_jeu.nb_roles", list_to_edit);
                                configPlugin.set("partie.roles_players." + t.getName(), role_t);
                                plugin.saveConfig();
                                if (configPlugin.getInt("partie.nb_total_role." + role_t) == 0) {
                                    configPlugin.set( "partie.nb_total_role." + role_t, 1);
                                    plugin.saveConfig();
                                } else {
                                    int n = configPlugin.getInt("partie.nb_total_role." + role_t);
                                    configPlugin.set("partie.nb_total_role." + role_t, n + 1);
                                    plugin.saveConfig();
                                }

                                player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                        "Tu as le rôle " + ChatColor.RED + role_t + ChatColor.WHITE + "."
                                );
                            }
                        } else {
                            Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    "Erreur d'attribution du rôle au joueur " + ChatColor.YELLOW + t.getName()
                            );
                        }
                    }

                    configPlugin.set("compos_jeu.nb_roles", old_amount_per_roles);
                    plugin.saveConfig();
                }
            } else {
                player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                        "Merci de donner un de ces argument: " +
                        "- " + ChatColor.YELLOW + "info [role]" + ChatColor.WHITE + " : donne les informations un rôle" +
                        "- " + ChatColor.YELLOW + "set [player] [index]" + ChatColor.WHITE + " : met à jour le role d'un joueur, si vous donnez 0 en index cela le retirera du jeu" +
                        "- " + ChatColor.YELLOW + "random" + ChatColor.WHITE + " : met à jour le role de chaque joueur connecté aléatoirement en respectant le nombre maximum de catégorie de rôle (3 Loup-Garou et non 5 par exemple)"
                );
            }
        }
        return false;
    }
}
