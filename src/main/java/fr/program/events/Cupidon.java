package fr.program.events;

import fr.program.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Cupidon {

    public static void cupidon_interaction(Inventory inv, InventoryClickEvent event, Player p, FileConfiguration config, Main plugin) {
        ItemStack head = event.getCurrentItem();
        int slot = event.getSlot();
        String name_player = head.getItemMeta().getDisplayName();
        Player target = Bukkit.getPlayer(name_player);

        if (target != null) {
            p.sendMessage( "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                    "Vous avez charmé " + ChatColor.YELLOW + target.getName() + ChatColor.WHITE
            );
            target.sendMessage( "[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                    "Vous avez été charmé par Cupidon !"
            );
            if (config.getString("partie.cupidon-1").equals("None")) {
                config.set("partie.cupidon-1", target.getName());
                plugin.saveConfig();

            } else {
                config.set("partie.cupidon-2", target.getName());
                plugin.saveConfig();
                p.closeInventory();

                Player p1 = Bukkit.getPlayer(config.getString("partie.cupidon-1"));
                Player p2 = Bukkit.getPlayer(config.getString("partie.cupidon-2"));

                if (p1 != null && p2 != null) {
                    p1.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                            "Vous êtes en couple avec " + ChatColor.LIGHT_PURPLE + p2.getName() + ChatColor.WHITE
                    );
                    p2.sendMessage("[" + ChatColor.DARK_RED + "Loup Garou" + ChatColor.WHITE + "] " +
                            "Vous êtes en couple avec " + ChatColor.LIGHT_PURPLE + p1.getName() + ChatColor.WHITE
                    );
                }
            }
        }
    }
}
