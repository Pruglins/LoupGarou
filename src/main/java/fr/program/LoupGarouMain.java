package fr.program;

import fr.program.cmds.DayCMD;
import fr.program.cmds.NightCMD;
import org.bukkit.craftbukkit.v1_19_R2.CraftServer;
import org.bukkit.plugin.java.JavaPlugin;

public class LoupGarouMain extends JavaPlugin {
    @Override
    public void onEnable() {
        System.out.println("(PLUGIN) Loup Garou: Starting...");
        //getCommand("night").setExecutor(new NightCMD());

        createCommand(new ConstructorCommands("night", "Met la nuit pour que le village s'endorme.", new NightCMD(), "nuit"));
        createCommand(new ConstructorCommands("day", "Met le jour pour que le village se lève.", new DayCMD(), "jour"));
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
