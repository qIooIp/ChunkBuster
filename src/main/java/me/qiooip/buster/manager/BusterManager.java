package me.qiooip.buster.manager;

import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BusterManager {

    private Map<UUID, BusterProfile> profiles;

    public BusterManager() {
        this.profiles = new HashMap<>();
    }

    public void onDisable() {
        this.profiles.clear();
    }

    public BusterProfile getProfile(Player player) {
        this.profiles.putIfAbsent(player.getUniqueId(), new BusterProfile());

        return this.profiles.get(player.getUniqueId());
    }

    public void cancelBusters(Player player) {
        this.getProfile(player).cancelBusters(player);
    }

    boolean isRunningIn(Chunk chunk) {
        return profiles.values().stream().anyMatch(profile -> profile.getBusters().stream()
        .anyMatch(buster -> buster.getChunk() == chunk));
    }
}
