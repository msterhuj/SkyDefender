package io.msterhuj.skydefender.core.rules;

import io.msterhuj.skydefender.SkyDefender;
import io.msterhuj.skydefender.core.rules.world.WorldRules;
import io.msterhuj.skydefender.core.rules.world.WorldType;
import lombok.Data;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;

@Data
public class Rules {

    private GameType gameType;
    private boolean hideDeath;
    private boolean hideDeathForTeams;
    private int pvpAllowedAtDay;

    private HashMap<WorldType, WorldRules> worlds;

    public Rules() {
        this.load();
    }

    public void load() {
        SkyDefender plugin = SkyDefender.getPlugin();

        FileConfiguration configuration = plugin.getConfig();
        ConfigurationSection rulesSection = configuration.createSection("rules");

        if (rulesSection.get("game-type") == "teams")  this.setGameType(GameType.TEAMS);
        else this.setGameType(GameType.ALONE);

        this.setHideDeath(rulesSection.getBoolean("hide-death"));
        this.setHideDeathForTeams(rulesSection.getBoolean("hide-death-for-teams"));

        this.setPvpAllowedAtDay(rulesSection.getInt("pvp-at-day"));

        // world configuration
        this.worlds = new HashMap<>();
        for (String s : rulesSection.getConfigurationSection("world").getKeys(false)) {
            ConfigurationSection section = configuration.getConfigurationSection("rules.world."+s);

            WorldRules worldRule = new WorldRules();
            worldRule.setEnabled(section.getBoolean("enable"));
            worldRule.setAtDay(section.getInt("at-day"));
            worldRule.setBypassDefender(section.getBoolean("bypass-defender"));

            if (s.equalsIgnoreCase("nether")) {
                worlds.put(WorldType.NETHER, worldRule);
            } else if (s.equalsIgnoreCase("end")) {
                worlds.put(WorldType.END, worldRule);
            }
        }
    }
}
