package io.msterhuj.skydefender.core.teams;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public enum Teams {
    RED("Red", Material.RED_WOOL, ChatColor.RED),
    GOLD("Orange", Material.ORANGE_WOOL, ChatColor.GOLD),
    YELLOW("Yellow", Material.YELLOW_WOOL, ChatColor.YELLOW),
    GREEN("Green", Material.GREEN_WOOL, ChatColor.GREEN),
    BLUE("Blue", Material.BLUE_WOOL, ChatColor.BLUE),
    PINK("Pink", Material.PINK_WOOL, ChatColor.LIGHT_PURPLE),
    LIGHT_PURPLE("Purple", Material.PURPLE_WOOL, ChatColor.DARK_PURPLE),
    WHITE("White", Material.WHITE_WOOL, ChatColor.WHITE),
    DEFENDER("Defender", Material.SHIELD, ChatColor.AQUA);

    private String name;
    private Material material;
    private ChatColor chatColor;

    Teams(String name, Material material, ChatColor chatColor) {
        this.name = name;
        this.material = material;
        this.chatColor = chatColor;
    }
}
