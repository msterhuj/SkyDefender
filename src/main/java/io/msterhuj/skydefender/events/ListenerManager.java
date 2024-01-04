package io.msterhuj.skydefender.events;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class ListenerManager {

    public ListenerManager(JavaPlugin javaPlugin) {
        PluginManager pluginManager = javaPlugin.getServer().getPluginManager();

        pluginManager.registerEvents(new PlayerJoin(), javaPlugin);
        pluginManager.registerEvents(new TeleporterEnter(), javaPlugin);
    }
}
