package fr.program;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class LoupGarouEvents implements Listener {
    LoupGarouMain plugin;
    public LoupGarouEvents(LoupGarouMain p) {
        this.plugin = p;
    }
    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Inventory inv = event.getInventory();
        Entity entity = event.getWhoClicked();
        if (entity instanceof Player p) {
            ItemStack item_current = event.getCurrentItem();

            if (item_current == null) {return;}

            ItemMeta meta = item_current.getItemMeta();
            PersistentDataContainer data = meta.getPersistentDataContainer();

            NamespacedKey key_sorciere_kill = new NamespacedKey(plugin, "sorciere-kill");
            NamespacedKey key_sorciere_ressusciter = new NamespacedKey(plugin, "sorciere-ressusciter");
            NamespacedKey key_sorciere_void = new NamespacedKey(plugin, "sorciere-void");

            if (data.has(key_sorciere_kill, PersistentDataType.DOUBLE)) {
                p.sendMessage("Vous aller tuer un joueur");
                event.setCancelled(true);
            } else if (data.has(key_sorciere_ressusciter, PersistentDataType.DOUBLE)) {
                String targetLoups = plugin.getConfig().getString("game.target-loups");
                if (targetLoups != null && !targetLoups.equals("None")) {
                    p.sendMessage("Vous ressuscitez le joueur visé par les loups : " + targetLoups + ".");
                    plugin.getConfig().set("roles.target-loups", "None");
                    plugin.saveConfig();
                } else {
                    p.sendMessage("Aucun joueur n'a été visé par les loups.");
                }
                event.setCancelled(true);
            } else if (data.has(key_sorciere_void, PersistentDataType.DOUBLE)) {
                p.closeInventory();
                event.setCancelled(true);
            } else {
                return;
            }
        }
    }

}
