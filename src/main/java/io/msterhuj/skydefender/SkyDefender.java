package io.msterhuj.skydefender;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import io.msterhuj.skydefender.commands.CommandManager;
import io.msterhuj.skydefender.core.SkyDefenderData;
import io.msterhuj.skydefender.events.ListenerManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;

@Getter
@Setter
public class SkyDefender extends JavaPlugin {

    private static SkyDefenderData skyDefenderDatas;
    private static SkyDefender plugin;

    public static SkyDefenderData getInstance() {
        return SkyDefender.skyDefenderDatas;
    }

    public static SkyDefender getPlugin() {
        return SkyDefender.plugin;
    }

    @Override
    public void onLoad() {

        plugin = this;

        if (!new File(this.getDataFolder() + "config.yml").exists()) {
            this.saveDefaultConfig();
            this.getLogger().info("Created config.yml");
        }

        if (!new File(this.getDataFolder() + "/data.json").exists()) {
            SkyDefender.skyDefenderDatas = new SkyDefenderData();
            this.saveSkyDefenderData();
        } else {
            this.loadSkyDefenderData();
        }
    }

    @Override
    public void onEnable() {
        new CommandManager(this);
        new ListenerManager(this);

        this.getLogger().info("Everything is ready !");
    }

    @Override
    public void onDisable() {
        this.saveSkyDefenderData();
    }

    private void loadSkyDefenderData() {
        try {
            Gson gson = new Gson();
            JsonReader reader = new JsonReader(new FileReader(this.getDataFolder().getPath() + "/data.json"));
            skyDefenderDatas = gson.fromJson(reader, SkyDefenderData.class);
            this.getLogger().info("Loaded data SkyDefender game");
        } catch (FileNotFoundException | StackOverflowError e) {
            this.getLogger().warning("An error occurred while trying to load the game from data.json");
            e.printStackTrace();
        }
    }

    private void saveSkyDefenderData() {
        Gson gson = new Gson();
        this.getLogger().info("Saving status of the game...");
        try {
            String data = gson.toJson(SkyDefender.skyDefenderDatas);
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.getDataFolder().getPath() + "/data.json"));
            bufferedWriter.write(data);
            bufferedWriter.close();
            this.getLogger().info("Game saved to data.json");
        } catch (IOException | StackOverflowError e) {
            this.getLogger().warning("An error occurred while trying to save the game to data.json");
            e.printStackTrace();
        }
    }
}
