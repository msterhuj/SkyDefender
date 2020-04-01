package io.msterhuj.skydefender.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkyDefender {

    private GameStatus status;

    public SkyDefender() {
        this.setStatus(GameStatus.WAITING);
    }
}
