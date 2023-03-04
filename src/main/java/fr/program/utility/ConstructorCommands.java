package fr.program.utility;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.Arrays;
import java.util.List;

public class ConstructorCommands extends Command {

    private final CommandExecutor executor;

    public ConstructorCommands(String name, String description, CommandExecutor executor, String permission, String... aliases) {
        super(name, description, "", Arrays.asList(aliases));
        this.executor = executor;
        this.setPermission(permission);
    }
    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        return executor.onCommand(sender, this, commandLabel, args);
    }
}
