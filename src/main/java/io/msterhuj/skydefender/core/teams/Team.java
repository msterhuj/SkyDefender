package io.msterhuj.skydefender.core.teams;

import lombok.*;
import org.bukkit.ChatColor;
import org.bukkit.Material;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    private String name;
    private Material material;
    private ChatColor chatColor;

}
