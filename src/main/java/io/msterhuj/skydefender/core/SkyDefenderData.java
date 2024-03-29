package io.msterhuj.skydefender.core;

import io.msterhuj.skydefender.core.rules.Rules;
import io.msterhuj.skydefender.core.teams.Team;
import io.msterhuj.skydefender.core.teams.TeamManager;
import io.msterhuj.skydefender.core.teleporter.Teleporter;
import io.msterhuj.skydefender.core.teleporter.TeleporterManager;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;


@Data
public class SkyDefenderData {

    private TeleporterManager teleporterManager;
    private TeamManager teamManager;

    private GameStatus status;
    private Set<SkyDefPlayer> players;
    private Set<SkyDefPlayer> randomTeamPlayers;
    private HashMap<String, Teleporter> teleporters;
    private ArrayList<Team> teams;
    private Rules rules;
    private int days;
    private long ticks;

    public SkyDefenderData() {
        this.setStatus(GameStatus.WAITING);

        this.teleporterManager = new TeleporterManager();
        this.teamManager = new TeamManager(true);

        this.players = new HashSet<>();
        this.randomTeamPlayers = new HashSet<>();
        this.teams = new ArrayList<>();
        this.teleporters = new HashMap<>();
        this.rules = new Rules();
    }
}
