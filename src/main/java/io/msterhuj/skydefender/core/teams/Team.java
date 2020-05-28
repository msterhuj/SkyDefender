package io.msterhuj.skydefender.core.teams;

import io.msterhuj.skydefender.core.SkyDefPlayer;
import lombok.*;

import java.util.HashMap;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Team {

    private Teams info;
    private HashMap<UUID, SkyDefPlayer> players;

    public Team() {
        this.info = Teams.BLUE;
    }

}
