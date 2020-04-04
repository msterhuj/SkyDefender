package io.msterhuj.skydefender.events;

import io.msterhuj.skydefender.SkyDefender;
import io.msterhuj.skydefender.core.teleporter.Teleporter;
import io.msterhuj.skydefender.core.teleporter.TeleporterType;
import io.msterhuj.skydefender.core.teleporter.TeleporterZone;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Set;

public class TeleporterEnter implements Listener {

    /**
     * ISLAND IN -> GROUND OUT
     * GROUND IN -> ISLAND OUT
     */

    @EventHandler
    public void teleporterEnter(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.PHYSICAL)) {
            if (event.getClickedBlock().getType() == Material.LIGHT_WEIGHTED_PRESSURE_PLATE){

                Location location = event.getClickedBlock().getLocation();
                Set<Teleporter> teleporters = SkyDefender.getInstance().getTeleporters();

                for (Teleporter teleporterINPUT :  teleporters) {
                    if (teleporterINPUT.getY() == location.getBlockY() && teleporterINPUT.getType() == TeleporterType.INPUT) {
                        for (Teleporter teleporterOUTPUT : teleporters) {
                            if (teleporterOUTPUT.getType() == TeleporterType.OUTPUT) {
                                // TODO MY BRAIN HAS BURNED
                            }
                        }
                    }
                }
            }
        }
    }
}
