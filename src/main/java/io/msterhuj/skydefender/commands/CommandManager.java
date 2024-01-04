package io.msterhuj.skydefender.commands;

import io.msterhuj.skydefender.commands.core.ICommandRoot;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    private List<ICommandRoot> commands = new ArrayList<>();

    public CommandManager(JavaPlugin javaPlugin) {
        this.commands.add(null); //TODO add root commands

        javaPlugin.getLogger().info("Loading commands");

        for(ICommandRoot command : commands) {
            javaPlugin.getCommand(command.getName()).setExecutor(command);
            javaPlugin.getCommand(command.getName()).setTabCompleter(command);
        }

    }
}
