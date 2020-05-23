package io.msterhuj.skydefender.core.teleporter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeleporterLocation {
    private UUID worldUUID;
    private int x, y, z;

}
