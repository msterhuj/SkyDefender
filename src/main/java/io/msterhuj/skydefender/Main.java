package io.msterhuj.skydefender;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    @Override
    public void onLoad() {
        if (!new File(getDataFolder() + "config.yml").exists()) {
            this.saveDefaultConfig();
        }

        getLogger().info("Loading");
    }

    @Override
    public void onEnable() {
        getLogger().info("Enable");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disable");
    }
}
