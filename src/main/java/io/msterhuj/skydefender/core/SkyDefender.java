package io.msterhuj.skydefender.core;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class SkyDefender {

    private GameStatus status;
    private Set<SkyDefPlayer> players;

    public SkyDefender() {
        this.setStatus(GameStatus.WAITING);
        this.players = new HashSet<>();
    }
}
