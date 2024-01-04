package io.msterhuj.skydefender.core;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class SkyDefPlayer {

    private UUID uuid;
    private String pseudo;
    private boolean dead;

    public SkyDefPlayer() {
        this.dead = false;
    }
}
