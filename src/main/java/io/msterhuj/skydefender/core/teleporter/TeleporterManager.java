package io.msterhuj.skydefender.core.teleporter;

import io.msterhuj.skydefender.SkyDefender;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TeleporterManager {

    /**
     * ISLAND IN -> GROUND OUT
     * GROUND IN -> ISLAND OUT
     */

    public void add(Player player, TeleporterZone zone, TeleporterType type) {

        Set<Teleporter> teleporters = SkyDefender.getInstance().getTeleporters();
        SkyDefender plugin = SkyDefender.getPlugin();

        Location location = player.getLocation();


        // remove if teleporter already exist
        for (Teleporter teleporter : teleporters) {
            if (teleporter.getZone() == zone && teleporter.getType() == type) {

                Location oldLocation = new Location(plugin.getServer().getWorld(teleporter.getWorldUUID()), teleporter.getX(),teleporter.getY(),teleporter.getZ());
                Block block = oldLocation.getBlock();
                block.setType(teleporter.getOldBlock());
                teleporters.remove(teleporter);
                break;
            }
        }


        // Add teleporter
        Teleporter teleporter = new Teleporter();
        teleporter.setWorldUUID(location.getWorld().getUID());
        teleporter.setX(location.getBlockX());
        teleporter.setY(location.getBlockY());
        teleporter.setZ(location.getBlockZ());
        teleporter.setOldBlock(location.getBlock().getType());
        teleporter.setType(type);
        teleporter.setZone(zone);

        if (type == TeleporterType.OUTPUT) {
            location.setY(location.getBlockY()-1);
            Block block = location.getBlock();
            block.setType(Material.DRIED_KELP_BLOCK);
        } else {
            Block block = location.getBlock();
            block.setType(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
        }
        SkyDefender.getInstance().getTeleporters().add(teleporter);
    }

    public Teleporter getOUTPUT(Player player, Location location) {
        Set<Teleporter> teleporters = SkyDefender.getInstance().getTeleporters();
        // TODO NEED DO REWRITE THIS FUNCTION
        Stream<Teleporter> teleporterINPUT = teleporters.stream().filter(teleporter -> teleporter.getType() == TeleporterType.INPUT);
        for (Teleporter teleporterZone : teleporterINPUT.collect(Collectors.toList())) {
            if (teleporterZone.getZ() == location.getBlockZ() && teleporterZone.getX() == location.getBlockX() && teleporterZone.getY() == location.getBlockY()) {
                Stream<Teleporter> teleporterTypeOUTPUT = teleporters.stream().filter(teleporter -> teleporter.getType() == TeleporterType.OUTPUT);
                for (Teleporter teleporterOUTPUTZONE : teleporterTypeOUTPUT.collect(Collectors.toList())) {
                    if (teleporterOUTPUTZONE.getZone() != teleporterZone.getZone()) {
                        return teleporterOUTPUTZONE;
                    }
                }
            }
        }
        return null;
    }
}
