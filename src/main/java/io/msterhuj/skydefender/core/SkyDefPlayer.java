package io.msterhuj.skydefender.core;

import io.msterhuj.skydefender.core.teams.TeamMode;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class SkyDefPlayer {

    private UUID uuid;
    private String pseudo;
    private TeamMode choiceMode;
    private boolean dead;

    public SkyDefPlayer() {
        this.dead = false;
    }
}
