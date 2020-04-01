package io.msterhuj.skydefender;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import io.msterhuj.skydefender.commands.SkyDefenderCommand;
import io.msterhuj.skydefender.commands.SkyDefenderTabCompletion;
import io.msterhuj.skydefender.core.SkyDefender;
import io.msterhuj.skydefender.events.PlayerJoin;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

@Getter@Setter
public class Main extends JavaPlugin {

    private Main main;

    private SkyDefender skyDefender;

    private Gson gson = new Gson();

    @Override
    public void onLoad() {
        if (!new File(this.getDataFolder() + "config.yml").exists()) {
            this.saveDefaultConfig();
            this.getLogger().info("Created config.yml");
        }
        if (!new File(this.getDataFolder() + "/SkyDefenderData.json").exists()) {
            this.skyDefender = new SkyDefender();
            this.saveSkyDefenderData();
            this.getLogger().info("Created SkyDefenderData.json for saving game");
        } else {
            this.getLogger().info("Loading game data from file...");
            try {
                JsonReader reader = new JsonReader(new FileReader(this.getDataFolder().getPath()+"/SkyDefenderData.json"));
                skyDefender = gson.fromJson(reader, SkyDefender.class);
                this.getLogger().info("Loaded data SkyDefender game");
            } catch (FileNotFoundException e) {
                this.getLogger().warning("Error when try to load data from SkyDefenderData.json");
                e.printStackTrace();
            }
        }

        this.main = this;
    }

    @Override
    public void onEnable() {

        this.getLogger().info("Loading commands");
        this.getCommand("skydefender").setExecutor(new SkyDefenderCommand());
        this.getCommand("skydefender").setTabCompleter(new SkyDefenderTabCompletion());

        this.getLogger().info("All is ready !");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("Saving status of game...");
        this.saveSkyDefenderData();
    }

    private void saveSkyDefenderData() {
        String data = this.gson.toJson(this.skyDefender);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.getDataFolder().getPath() + "/SkyDefenderData.json"));
            bufferedWriter.write(data);
            bufferedWriter.close();
            this.getLogger().info("Success saved data !");
        } catch (IOException e) {
            this.getLogger().warning("Error when try to save data to SkyDefenderData.json");
            e.printStackTrace();
        }
    }
}
