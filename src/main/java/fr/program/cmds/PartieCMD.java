package fr.program.cmds;

import fr.program.Main;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;
import java.util.List;

public class PartieCMD implements CommandExecutor {

    private final Main plugin;
    public PartieCMD(Main main) {
        this.plugin = main;
    }

    private int safeParseInt(String value) {
        if (value == null || value.isEmpty()) {
            return 0;
        }

        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private int getAmount_Mechant(FileConfiguration config) {
        String amount_of_wolf = config.getString("partie.nb_total_role.Loup Garou");
        String amount_of_mechant_loup = config.getString("partie.nb_total_role.Grand Méchant loup");
        String amount_of_loup_blanc = config.getString("partie.nb_total_role.Loup Garou Blanc");
        String amount_of_infect = config.getString("partie.nb_total_role.Infect Père des loups");
        String amount_of_enfant = config.getString("partie.nb_total_role.Enfant sauvage");
        String amount_of_chien_loup = config.getString("partie.nb_total_role.Chien Loup");

        return safeParseInt(amount_of_wolf)
                + safeParseInt(amount_of_mechant_loup)
                + safeParseInt(amount_of_loup_blanc)
                + safeParseInt(amount_of_infect)
                + safeParseInt(amount_of_enfant)
                + safeParseInt(amount_of_chien_loup)
                ;
    }

    private int getAmount_Gentils(FileConfiguration config) {
        return safeParseInt(config.getString("partie.nb_total_role.Villageois")) +
                safeParseInt(config.getString("partie.nb_total_role.Cupidon")) +
                safeParseInt(config.getString("partie.nb_total_role.Voyante")) +
                safeParseInt(config.getString("partie.nb_total_role.Soeurs")) +
                safeParseInt(config.getString("partie.nb_total_role.Petite fille")) +
                safeParseInt(config.getString("partie.nb_total_role.Renard")) +
                safeParseInt(config.getString("partie.nb_total_role.Chevalier à l'épée rouillée")) +
                safeParseInt(config.getString("partie.nb_total_role.Ancien")) +
                safeParseInt(config.getString("partie.nb_total_role.Sorcière")) +
                safeParseInt(config.getString("partie.nb_total_role.Montreur d'ours")) +
                safeParseInt(config.getString("partie.nb_total_role.Chasseur")) +
                safeParseInt(config.getString("partie.nb_total_role.Enfant sauvage")) +
                safeParseInt(config.getString("partie.nb_total_role.Chien Loup")) +
                safeParseInt(config.getString("partie.nb_total_role.Ange")) +
                safeParseInt(config.getString("partie.nb_total_role.Joueur de flûte"))
                ;
    }


    private void vote_eliminatePossibleLoupGarou() {
        Inventory inv = Bukkit.createInventory(null, 45, "Vote : Eliminer un joueur");

        int slot = 10;

        for (Player p : Bukkit.getOnlinePlayers()) {
            ItemStack head_p = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta head_meta = (SkullMeta) head_p.getItemMeta();
            head_meta.setDisplayName(p.getName());
            head_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            head_meta.setOwningPlayer(Bukkit.getOfflinePlayer(p.getUniqueId()));
            head_p.setItemMeta(head_meta);
            inv.setItem(slot, head_p);
            slot++;
        }

        for (Player p : Bukkit.getOnlinePlayers()) {
            p.openInventory(inv);
        }
    }

    private void UI_Cupidon(Player cupidon) {
        Inventory inv = Bukkit.createInventory(null, 45, "Cupidon");
        for (Player p : Bukkit.getOnlinePlayers()) {
            ItemStack head_p = new ItemStack(Material.PLAYER_HEAD, 1);
            SkullMeta head_meta = (SkullMeta) head_p.getItemMeta();
            head_meta.setDisplayName(p.getName());
            head_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            head_meta.setOwningPlayer(Bukkit.getOfflinePlayer(p.getUniqueId()));
            head_p.setItemMeta(head_meta);
            inv.addItem(head_p);
        }

        new BukkitRunnable() {
            int i = 20;
            @Override
            public void run() {
                if (i == 1) {
                    cupidon.setExp(i);
                }
                i--;
            }
        }.runTaskTimer(plugin, 0, 20);

        cupidon.openInventory(inv);
    }

    private void startNight(FileConfiguration config) {
        if (config.getInt("partie.nuit") == 1) {
            for (Player p : Bukkit.getOnlinePlayers()) {
                String role = config.getString("partie.roles_players." + p.getName());
                if (role.equals("Cupidon")) {
                    Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                            "Cupidon se réveille !"
                    );
                    p.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                            "Vous avez 20 secondes pour effectuer votre action : mettre en couple deux personnes, si l'une d'elle meurt l'autre meurt de chagrin également."
                    );
                    UI_Cupidon(p);
                    config.set("partie.current_role", "Cupidon");
                }

                new BukkitRunnable() { @Override public void run() { /*Waiting*/} }.runTaskLater(plugin, 20 * 30); // 30s -> 20 * 30
            }
        }
    }

    private boolean managerStart_Game(FileConfiguration config, Player author_player) {
        List<String> GameCurrent_Players = plugin.getConfig().getStringList("partie.joueurs");

        for (String p_name : GameCurrent_Players) {
            Player p = Bukkit.getPlayer(p_name);
            if (p != null) {
                PotionEffect effect = new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 10); // 20ticks -> 1s, 5min -> 20*300
                p.addPotionEffect(effect);
            }
        }

        Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                "Dans cette partie, il peut y avoir au total "
                + ChatColor.RED + getAmount_Mechant(config) + ChatColor.WHITE + " Loups-Garous, et " + ChatColor.GREEN
                + getAmount_Gentils(config) + ChatColor.WHITE + " Villageois."
        );

        startNight(config);

        return true; // Fin de la partie
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        FileConfiguration configPlugin = plugin.getConfig();

        if (sender instanceof Player player) {
            if (args.length > 0) {
                String selection_mode = args[0];

                if (selection_mode.equals("start") || selection_mode.equals("commencer")) {
                    Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                            "La partie va commencer !"
                    );

                    new BukkitRunnable() {

                        int i = 3;
                        @Override
                        public void run() {
                            for (Player p : Bukkit.getOnlinePlayers()) {
                                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
                            }
                            Bukkit.broadcastMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                                    "La partie commence dans " + ChatColor.GREEN + i + ChatColor.WHITE + " !"
                            );
                            if (i == 1) {
                                this.cancel();
                                managerStart_Game(configPlugin, player);
                            }
                            i--;
                        }
                    }.runTaskTimer(plugin, 0, 20*1); // 1s -> 20ticks
                } else if (selection_mode.equals("joueur") || selection_mode.equals("joueurs") || selection_mode.equals("player") || selection_mode.equals("players")) {
                    List<String> listes_players_game = configPlugin.getStringList("partie.joueurs");
                    player.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "]" +
                            "Voici la liste de tout les joueurs qui participent au jeu : "
                    );
                    for (String name_p : listes_players_game) {
                        player.sendMessage("- " + ChatColor.YELLOW + Bukkit.getPlayer(name_p).getName() + ChatColor.WHITE);
                    }
                    return true;
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

    private void reloadConfig(FileConfiguration config) {
        config.set("compos_jeu.roles", getDefaultRoles());
        config.set("compos_jeu.nb_roles", getDefaultRoleNumbers());
        config.set("partie.joueurs", getDefaultPlayers());
        config.set("partie.roles_players", null);
        config.set("partie.nb_total_role", null);
        config.set("partie.nuit", 1);
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
