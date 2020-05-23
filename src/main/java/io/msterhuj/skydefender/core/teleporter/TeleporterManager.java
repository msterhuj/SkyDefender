package io.msterhuj.skydefender.core.teleporter;

import io.msterhuj.skydefender.SkyDefender;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TeleporterManager {

    public void add(Player player, TeleporterZone zone, TeleporterType type) {

        //TODO check si le téléporteur est déjà présent par rapport au nom
        HashMap<String, Teleporter> teleporters = SkyDefender.getInstance().getTeleporters();
        SkyDefender plugin = SkyDefender.getPlugin();

        Location location = player.getLocation();

        // remove if teleporter already exist
        for (Teleporter teleporter : teleporters.values()) {
            if (teleporter.getZone() == zone && teleporter.getType() == type) {

                Location oldLocation = new Location(plugin.getServer().getWorld(teleporter.getWorldUUID()), teleporter.getX(),teleporter.getY(),teleporter.getZ());
                Block block = oldLocation.getBlock();
                block.setType(teleporter.getOldBlock());
                teleporters.remove(teleporter);
                break;
            }
        }

        // Add teleporter / nouveau téléporteur
        TeleporterLocation tpLocation = new TeleporterLocation(location.getWorld().getUID(),
                location.getBlockX(),location.getBlockY(),location.getBlockZ());

        Teleporter teleporter;
        if (type == TeleporterType.INPUT) {
            teleporter = new Teleporter(zone, type, location.getBlock().getType(),null, tpLocation );
        } else {
            teleporter = new Teleporter(zone, type, location.getBlock().getType(), tpLocation, null );
        }
        //-------------------------------------

        if (type == TeleporterType.OUTPUT) {
            location.setY(location.getBlockY()-1);
            Block block = location.getBlock();
            block.setType(Material.DRIED_KELP_BLOCK);
        } else {
            Block block = location.getBlock();
            block.setType(Material.LIGHT_WEIGHTED_PRESSURE_PLATE);
        }

        //TODO set la key
        SkyDefender.getInstance().getTeleporters().put("string id", teleporter);
    }

    //TODO remove l'ancien bloc de tp / retirer entièrement un téléporteur
    private boolean removeTeleporter() {

    }

    public Teleporter getTeleporterFrom(Player player, Location location) {
        HashMap<String, Teleporter> teleporters = SkyDefender.getInstance().getTeleporters();

        return teleporters.values().stream().filter(teleporter -> isTeleporterLocation(teleporter.getFrom(), location)).collect(Collectors.toList()).get(0);
    }

    private boolean isTeleporterLocation(TeleporterLocation teleporterLocation, Location location) {
        return location.getWorld() != null && location.getWorld().getUID() == teleporterLocation.getWorldUUID()
                && teleporterLocation.getX() == location.getBlockX()
                && teleporterLocation.getY() == location.getBlockY()
                && teleporterLocation.getZ() == location.getBlockZ();
    }
}
