package fr.program;

import org.bukkit.plugin.java.JavaPlugin;

public class LoupGarouMain extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("(PLUGIN) Loup Garou: Starting...");
    }

    @Override
    public void onDisable() {
        System.out.println("(PLUGIN) Loup Garou: Ending...");
    }
}
