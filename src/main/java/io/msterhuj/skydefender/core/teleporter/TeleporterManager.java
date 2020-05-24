package io.msterhuj.skydefender.core.teleporter;

import io.msterhuj.skydefender.SkyDefender;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class TeleporterManager {

    public void add(Player player, String teleporterName, TeleporterType type) {

        HashMap<String, Teleporter> teleporters = SkyDefender.getInstance().getTeleporters();

        Teleporter teleporter;

        // remove if teleporter location already exist
        try {
            Teleporter teleporterFind = teleporters.values().stream().filter(t -> teleporterName.equalsIgnoreCase(t.getName())).findFirst().get();
            if (teleporterFind.getName().equalsIgnoreCase(teleporterName)) {
                if (type == TeleporterType.INPUT) {
                    teleporterFind.getFrom().removeBlock();
                    teleporterFind.setFrom(null);
                } else if (type == TeleporterType.OUTPUT) {
                    teleporterFind.getTo().removeBlock();
                    teleporterFind.setTo(null);
                }
            }
            teleporter = teleporterFind;
        } catch (NoSuchElementException e) {
            teleporter = new Teleporter(teleporterName);
        }

        // add new teleporter
        TeleporterLocation newTpLocation = new TeleporterLocation(player, type);
        newTpLocation.setupBock(player);

        if (type == TeleporterType.INPUT) {
            teleporter.setFrom(newTpLocation);
        } else if (type == TeleporterType.OUTPUT){
            teleporter.setTo(newTpLocation);
        }

        SkyDefender.getInstance().getTeleporters().put(teleporterName, teleporter);
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
