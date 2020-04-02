package io.msterhuj.skydefender.events;

import io.msterhuj.skydefender.SkyDefender;
import io.msterhuj.skydefender.core.GameStatus;
import io.msterhuj.skydefender.core.SkyDefenderData;
import org.bukkit.GameMode;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoin implements Listener {

    SkyDefenderData skyDefenderDatas = SkyDefender.getInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (skyDefenderDatas.getStatus()!= GameStatus.WAITING) {
            event.getPlayer().setGameMode(GameMode.SPECTATOR);
        }
    }
}
