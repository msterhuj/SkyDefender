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
    private boolean oldPVP;

    private HashMap<WorldType, WorldRules> worlds;

    public Rules() {
        this.load();
    }

    public void load() {
        SkyDefender plugin = SkyDefender.getPlugin();

        FileConfiguration configuration = plugin.getConfig();
        if (configuration.get("rules.game-type") == "teams") {
            this.setGameType(GameType.TEAMS);
        } else this.setGameType(GameType.ALONE);
        this.setHideDeath(configuration.getBoolean("rules.hide-death"));
        this.setHideDeathForTeams(configuration.getBoolean("rules.hide-death-for-teams"));
        this.setPvpAllowedAtDay(configuration.getInt("rules.pvp.at-day"));
        this.setOldPVP(configuration.getBoolean("rules.pvp.old-mode"));

        // world configuration
        this.worlds = new HashMap<>();
        for (String s : configuration.getConfigurationSection("rules.world").getKeys(false)) {
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
