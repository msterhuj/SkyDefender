package io.msterhuj.skydefender.commands;

import org.bukkit.plugin.java.JavaPlugin;

public class CommandManager {

    public CommandManager(JavaPlugin javaPlugin) {
        javaPlugin.getLogger().info("Loading commands");
        javaPlugin.getCommand("skydefender").setExecutor(new SkyDefenderCommand());
        javaPlugin.getCommand("skydefender").setTabCompleter(new SkyDefenderTabCompletion());
    }
}
