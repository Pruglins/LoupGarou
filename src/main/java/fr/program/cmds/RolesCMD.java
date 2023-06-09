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

public class RolesCMD implements CommandExecutor {

    private final Main plugin;
    public RolesCMD(Main main) {
        this.plugin = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        FileConfiguration configPlugin = plugin.getConfig();

        if (sender instanceof Player player) {
            if (args.length >= 2) {
                String selection_mode = args[0];
                if (selection_mode.equals("info")) {
                    String role = args[1];
                    switch (role) {
                        case "Loup-Garou":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    "Se réveille chaque nuit avec son groupe pour éliminer un membre du village qui ne fait pas parti de sa meute. Le joueur voté meurt au lever du jour"
                            );
                            break;
                        case "Infect-Père-des-Loups":
                        case "Infect-Père-Loups":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    "Il dévore un membre du village avec ses semblables mais s'il le souhaite il peut décider pour une nuit seulement de rendre la personne voté infecté qui devient un loup garou."
                            );
                            break;
                        case "Grand-Méchant-Loup":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    "Il dévore un membre du village avec ses semblables mais peut se réveiller une deuxième fois si aucun Loup-Garou, Enfant-Sauvage ou Chien-Loup n'est mort."
                            );
                            break;
                        case "Villageois":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    "Aucun réel impact dans la partie, il participe seulement au vote pour éliminer un joueur de la partie."
                            );
                            break;
                        case "Cupidon":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    "Se réveille la première nuit pour rendre amoureux deux joueurs, peu import leur rôle."
                            );
                            break;
                        case "Voyante":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    "Peut regarder le rôle d'un joueur de son choix, une fois par nuit."
                            );
                            break;
                        case "2-Soeurs":
                        case "Deux-Soeurs":
                        case "Soeurs":
                        case "Soeur":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    "Se réveillent chaque nuit pour discuter silencieusement des décisions à prendre si elles le souhaitent, ou peuvent tout simplement se voir et dormir directement.."
                            );
                            break;
                        case "Petite-Fille":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    "Ouvrir les yeux un peu de temps en temps pour entrevoir des actions des joueurs pendant la nuit. Elle n'a pas le droit de se faire passer pour un loup garou."
                            );
                            break;
                        case "Renard":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    "Désigne un groupe de 3 personnes et sait si un loup garou s'y trouve, sans savoir qui c'est réellement."
                            );
                            break;
                        case "Chevalier-Epée-Rouillée":
                        case "Chevalier-Epee-Rouillee":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    "S'il meurt, le premier loup garou qui est a sa gauche meurt suite au blessure de l'épée qui l'a empoisonné."
                            );
                            break;
                        case "Ancien":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    "Il a une seconde vie lorsqu'il se fait dévorer par un/des Loups-Garou. Si le village décide de le tuer pendant la nuit (par exemple la Sorcière) ou lors d'un vote, ils n'ont plus accès aux pouvoirs."
                            );
                            break;
                        case "Sorcière":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    "Elle peut donner une potion qui fait survivre une victime des loups garou ou peut tuer quelqu'un de son choix, ou rien faire."
                            );
                            break;
                        case "Montreur-Ours":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    "Chaque matin, s'il y a un loup garou voisin de ce joueur, il est mis au courant."
                            );
                            break;
                        case "Chasseur":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    "Lors de sa mort provoqué par les Loups-Garou ou le village, il peut décider d'éliminer quelqu'un."
                            );
                            break;
                        case "Enfant-Sauvage":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    "Désigne un joueur modèle la première nuit, si ce dernier meurt, l'enfant sauvage devient loup garou pour les prochaines nuits."
                            );
                            break;
                        case "Chien-Loup":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    "Décide s'il souhaite faire parti des loups ou des membres du village la première nuit."
                            );
                            break;
                        case "Loup-Garou-Blanc":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    "Il élimine les membres du village avec ces confrères mais une nuit sur deux il doit voter un loup garou, son but est de rester le dernier survivant de la partie."
                            );
                            break;
                        case "Joueur-Flûte":
                        case "Flûte":
                        case "Joueur-Flute":
                        case "Flute":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    "Choisi 2 joueurs chaque nuit et s'il arrive à charmer tout les les joueurs il gagne la partie."
                            );
                            break;
                        case "Ange":
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    "S'il est éliminé lors du premier tour, il gagne la partie."
                            );
                            break;

                        default:
                            player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                                    "Ce rôle est introuvable ! S'il y a pas un simple mot, merci de mettre un '-' entre chaque mot."
                            );
                            break;
                    }
                }
            } else {
                player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                        "Merci de donner un de ces argument: " +
                        "- " + ChatColor.YELLOW + "info [role]" + ChatColor.WHITE + " : donne les informations un rôle" +
                        "- " + ChatColor.YELLOW + "set [player]" + ChatColor.WHITE + " : met à jour le role d'un joueur"
                );
            }
        }
        return false;
    }
}
