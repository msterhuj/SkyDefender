package io.msterhuj.skydefender.core.teleporter;

import lombok.Data;

import lombok.NoArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@Data
@NoArgsConstructor
public class Teleporter {

    private String name;
    private TeleporterLocation from;
    private TeleporterLocation to;

    public Teleporter(String name) {
        this.name = name;
    }

    public void teleportPlayer(Player player, boolean keepPlayerDirection) {

        Location toLocation = this.to.getLocation();
        toLocation.setX(toLocation.getBlockX() + 0.5);
        toLocation.setZ(toLocation.getBlockZ() + 0.5);

        if (keepPlayerDirection) toLocation.setDirection(player.getLocation().getDirection());

        player.teleport(toLocation);
    }
}
