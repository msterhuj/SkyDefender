package io.msterhuj.skydefender.core;

import io.msterhuj.skydefender.core.teams.Team;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class SkyDefPlayer {

    private UUID uuid;
    private Team team;
    private boolean dead;

    public SkyDefPlayer() {
        this.dead = false;
    }
}
