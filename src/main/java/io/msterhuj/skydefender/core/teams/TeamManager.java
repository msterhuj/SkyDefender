package io.msterhuj.skydefender.core.teams;

import io.msterhuj.skydefender.SkyDefender;

import java.util.ArrayList;

public class TeamManager {

    public TeamManager(boolean registerAllTeams) {
        if (registerAllTeams) registerAllTeam();
        else this.registerDefaultTeams();
    }

    public void registerAllTeam() {
        for (Teams teams : Teams.values()) {
            registerNewTeam(teams);
        }
    }

    public void registerNewTeam(Teams teams) {
        SkyDefender.getInstance().getTeams().add(new Team(teams));
    }

    public void registerDefaultTeams() {
        this.registerNewTeam(Teams.SPECTATOR);
        this.registerNewTeam(Teams.DEFENDER);
    }
}
