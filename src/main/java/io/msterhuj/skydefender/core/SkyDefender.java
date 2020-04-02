package io.msterhuj.skydefender.core;

import io.msterhuj.skydefender.core.teams.Team;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class SkyDefender {

    private GameStatus status;
    private Set<SkyDefPlayer> players;
    private ArrayList<Team> teams;

    public SkyDefender() {
        this.setStatus(GameStatus.WAITING);
        this.players = new HashSet<>();
    }
}
