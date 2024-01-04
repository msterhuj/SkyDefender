package io.msterhuj.skydefender.core.teams;

import io.msterhuj.skydefender.core.SkyDefPlayer;
import lombok.*;

import java.util.HashMap;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    private Teams teamColor;
    private HashMap<UUID, SkyDefPlayer> players;

    public void addPlayer(SkyDefPlayer skyDefPlayer) {
        skyDefPlayer.setTeam(this);
        this.players.put(skyDefPlayer.getUuid(), skyDefPlayer);
    }

    public void removePlayer(UUID uuid) {
        this.players.get(uuid).setTeam(null);
        this.players.remove(uuid);
    }
}
