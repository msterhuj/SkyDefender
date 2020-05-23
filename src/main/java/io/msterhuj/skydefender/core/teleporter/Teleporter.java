package io.msterhuj.skydefender.core.teleporter;

import io.msterhuj.skydefender.SkyDefender;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.bukkit.Location;
import org.bukkit.Material;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teleporter {

    private TeleporterZone zone;
    private TeleporterType type;
    private Material oldBlock;
    private TeleporterLocation from;
    private TeleporterLocation to;

    public Location getToLocation() {
        return new org.bukkit.Location(
                SkyDefender.getPlugin().getServer().getWorld(this.to.getWorldUUID()),
                this.to.getX(),
                this.to.getY(),
                this.to.getZ()
        );
    }
}
