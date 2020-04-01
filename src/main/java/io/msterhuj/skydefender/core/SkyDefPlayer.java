package io.msterhuj.skydefender.core;

import io.msterhuj.skydefender.core.teams.Teams;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class SkyDefPlayer {

    UUID uuid;
    private Teams team;
    private boolean dead;

    public SkyDefPlayer() {
        this.team = Teams.SPECTATOR;
        this.dead = false;
    }
}
