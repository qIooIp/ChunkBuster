package me.qiooip.buster.manager;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BusterManager {

    private Map<UUID, BusterProfile> profiles;

    public BusterManager() {
        this.profiles = new HashMap<>();
    }

    public BusterProfile getProfile(Player player) {
        this.profiles.putIfAbsent(player.getUniqueId(), new BusterProfile());

        return this.profiles.get(player.getUniqueId());
    }
}
