package fr.program;

import fr.program.events.Cupidon;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class EventsListener implements Listener {

    private final Main plugin;

    public EventsListener(Main main) {
        this.plugin = main;
    }

    @EventHandler
    public void onClickInventoryEvent(InventoryClickEvent event) {
        FileConfiguration config = plugin.getConfig();

        Inventory inv = event.getInventory();
        Player p = event.getWhoClicked().getKiller();

        String currentGameRole = config.getString("partie.current_role");
        String role_player = config.getString("partie.joueurs." + p.getName());

        if (currentGameRole.equals("Cupidon") && role_player.equals("Cupidon")) {
            Cupidon.cupidon_interaction(inv, event, p, config, plugin);
        }
    }
}
