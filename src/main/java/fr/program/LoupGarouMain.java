package fr.program;

import fr.program.cmds.DayCMD;
import fr.program.cmds.NightCMD;
import fr.program.cmds.PartieCMD;
import org.bukkit.craftbukkit.v1_19_R2.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

public class LoupGarouMain extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("(PLUGIN) Loup Garou: Starting...");
        //getCommand("night").setExecutor(new NightCMD());

        saveDefaultConfig();

        // Commande classiques:
        createCommand(new ConstructorCommands("night",
                "Met la nuit pour que le village s'endorme.",
                new NightCMD(),
                "loupgarou.nuit",
                "nuit"));
        createCommand(new ConstructorCommands("day",
                "Met le jour pour que le village se lève.",
                new DayCMD(),
                "loupgarou.nuit",
                "jour"));

        // Création de la partie

        createCommand(new ConstructorCommands(
                "partie",
                "Préparer la partie.",
                new PartieCMD(this),
                "loupgarou.partie",
                "game"));
    }

    private void createCommand(ConstructorCommands cmd) {
        CraftServer server = (CraftServer) getServer();
        server.getCommandMap().register(getName(), cmd);
    }

    @Override
    public void onDisable() {
        System.out.println("(PLUGIN) Loup Garou: Ending...");
    }
}
