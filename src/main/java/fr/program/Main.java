package fr.program;

import fr.program.cmds.PartieCMD;
import fr.program.cmds.RolesCMD;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("[LOUP GAROU] Enabled.");

        this.saveDefaultConfig();
        this.saveResource("compos_jeu.roles", true);
        this.saveResource("compos_jeu.nb_roles", true);

        this.getCommand("partie").setExecutor(new PartieCMD(this));
        this.getCommand("roles").setExecutor(new RolesCMD(this));
    }

    @Override
    public void onDisable() {
        System.out.println("[LOUP GAROU] Disabled.");
    }
}