package io.msterhuj.skydefender.events;

import io.msterhuj.skydefender.SkyDefender;
import io.msterhuj.skydefender.core.GameStatus;
import io.msterhuj.skydefender.core.SkyDefenderData;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    SkyDefenderData skyDefenderData = SkyDefender.getInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (skyDefenderData.getStatus() == GameStatus.WAITING) {
            if (event.getPlayer().isOp()) {
                event.getPlayer().setGameMode(GameMode.CREATIVE);
            } else {
                event.getPlayer().setGameMode(GameMode.ADVENTURE);
            }
        }
    }
}
