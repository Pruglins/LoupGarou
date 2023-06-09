package fr.program;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("[LOUP GAROU] Enabled.");
    }

    @Override
    public void onDisable() {
        System.out.println("[LOUP GAROU] Disabled.");
    }
}