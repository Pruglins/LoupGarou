package fr.program;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

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

        }
    }
}
