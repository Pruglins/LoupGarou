package fr.program.events;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Cupidon {
    public static void cupidon_interaction(Inventory inv, InventoryClickEvent event, Player p, FileConfiguration config) {
        ItemStack head = event.getCurrentItem();
        String name_player = head.getItemMeta().getDisplayName();
        Player target = Bukkit.getPlayer(name_player);

        if (target != null) {

        }
    }
}
