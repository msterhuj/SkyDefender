package io.msterhuj.skydefender.core.teleporter;

import io.msterhuj.skydefender.SkyDefender;
import lombok.Data;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.UUID;

@Data
public class Teleporter {

    private UUID worldUUID;
    private TeleporterZone zone;
    private TeleporterType type;
    private Material oldBlock;
    private int x, y, z;

    public Location getLocation() {
        return new org.bukkit.Location(
                SkyDefender.getPlugin().getServer().getWorld(this.worldUUID),
                this.x,
                this.y,
                this.z
        );
    }
}
