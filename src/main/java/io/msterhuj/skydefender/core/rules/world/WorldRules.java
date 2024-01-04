package io.msterhuj.skydefender.core.rules.world;

import lombok.Data;

@Data
public class WorldRules {
    private boolean enabled;
    private int atDay;
    private boolean bypassDefender;
}
