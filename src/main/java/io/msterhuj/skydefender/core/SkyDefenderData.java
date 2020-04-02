package io.msterhuj.skydefender.core;

import io.msterhuj.skydefender.core.teams.Team;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class SkyDefenderData {

    private GameStatus status;
    private Set<SkyDefPlayer> soloPlayers;
    private Set<SkyDefPlayer> randomTeamPlayers;
    private ArrayList<Team> teams;
    private int days;
    private long ticks;

    public SkyDefenderData() {
        this.setStatus(GameStatus.WAITING);
        this.soloPlayers = new HashSet<>();
        this.randomTeamPlayers = new HashSet<>();
    }

}
