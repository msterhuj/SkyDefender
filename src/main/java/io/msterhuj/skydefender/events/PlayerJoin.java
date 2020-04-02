package io.msterhuj.skydefender.events;

import io.msterhuj.skydefender.core.GameStatus;
import io.msterhuj.skydefender.core.SkyDefender;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    SkyDefender skyDefender;

    public PlayerJoin(SkyDefender skyDefender) {
        this.skyDefender = skyDefender;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (skyDefender.getStatus()!= GameStatus.WAITING) {
            event.getPlayer().setGameMode(GameMode.SPECTATOR);
        }
    }
}
