package io.msterhuj.skydefender.core.teams;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum Teams {
    RED(new Team("Red", Material.RED_WOOL, ChatColor.RED)),
    GOLD(new Team("Orange", Material.ORANGE_WOOL, ChatColor.GOLD)),
    YELLOW(new Team("Yellow", Material.YELLOW_WOOL, ChatColor.YELLOW)),
    GREEN(new Team("Green", Material.GREEN_WOOL, ChatColor.GREEN)),
    BLUE(new Team("Blue", Material.BLUE_WOOL, ChatColor.BLUE)),
    PINK(new Team("Pink", Material.PINK_WOOL, ChatColor.LIGHT_PURPLE)),
    LIGHT_PURPLE(new Team("Purple", Material.PURPLE_WOOL, ChatColor.DARK_PURPLE)),
    WHITE(new Team("WHITE", Material.WHITE_WOOL, ChatColor.WHITE)),
    SPECTATOR(new Team("Spectator", Material.BARRIER, ChatColor.DARK_GRAY)),
    DEFENDER(new Team("Defender", Material.SHIELD, ChatColor.AQUA));

    Teams(Team team) {}
}
