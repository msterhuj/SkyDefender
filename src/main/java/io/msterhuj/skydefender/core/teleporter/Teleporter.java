package io.msterhuj.skydefender.core.teleporter;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teleporter {

    private TeleporterLocation from;
    private TeleporterLocation to;

    public void teleportPlayer(Player player, boolean keepPlayerDirection) {
        if (keepPlayerDirection) {
            Location toLocation = this.to.getLocation();
            toLocation.setDirection(player.getLocation().getDirection());
            toLocation.setX(toLocation.getBlockX() + 0.5);
            toLocation.setZ(toLocation.getBlockZ() + 0.5);
            player.teleport(toLocation);
        } else {
            player.teleport(this.to.getLocation());
        }
    }
}
