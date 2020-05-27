package io.msterhuj.skydefender.core.teleporter;

import io.msterhuj.skydefender.SkyDefender;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.NoSuchElementException;

public class TeleporterManager {

    public void add(Player player, String teleporterName, TeleporterType type) {

        HashMap<String, Teleporter> teleporters = SkyDefender.getInstance().getTeleporters();

        Teleporter teleporter;

        // remove if teleporter location already exist
        try {
            Teleporter teleporterFind = teleporters.values().stream().filter(t -> teleporterName.equalsIgnoreCase(t.getName())).findFirst().get();
            if (type == TeleporterType.INPUT) {
                teleporterFind.getFrom().removeBlock();
                teleporterFind.setFrom(null);
            } else if (type == TeleporterType.OUTPUT) {
                teleporterFind.getTo().removeBlock();
                teleporterFind.setTo(null);
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

    public Teleporter getTeleporter(Location location) {
        try {
            return SkyDefender.getInstance().getTeleporters().values()
                    .stream().filter(t ->
                               t.getFrom().getX() == location.getBlockX()
                            && t.getFrom().getY() == location.getBlockY()
                            && t.getFrom().getZ() == location.getBlockZ()
                    ).findFirst().get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }
}
