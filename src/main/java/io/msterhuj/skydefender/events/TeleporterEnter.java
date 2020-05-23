package io.msterhuj.skydefender.events;

import io.msterhuj.skydefender.SkyDefender;
import io.msterhuj.skydefender.core.teleporter.Teleporter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class TeleporterEnter implements Listener {

    /**
     * ISLAND IN -> GROUND OUT
     * GROUND IN -> ISLAND OUT
     */

    @EventHandler
    public void teleporterEnter(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.PHYSICAL)) {
            if (event.getClickedBlock().getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE){
                Teleporter teleporter = SkyDefender.getInstance().getTeleporterManager().getTeleporterFrom(event.getPlayer(), event.getClickedBlock().getLocation());
                if (teleporter != null) {
                    Location location = teleporter.getToLocation();
                    location.setDirection(event.getPlayer().getLocation().getDirection());
                    event.getPlayer().teleport(location);
                }
            }
        }
    }
}
