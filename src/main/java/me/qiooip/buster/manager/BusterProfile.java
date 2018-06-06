package me.qiooip.buster.manager;

import me.qiooip.buster.config.Config;
import me.qiooip.buster.config.Language;
import me.qiooip.buster.utils.ItemUtils;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class BusterProfile {

    private static Map<UUID, BusterProfile> profiles = new HashMap<>();

    private Set<BusterData> busters;

    private BusterProfile() {
        this.busters = new HashSet<>();
    }

    public static BusterProfile getProfile(Player player) {
        profiles.putIfAbsent(player.getUniqueId(), new BusterProfile());
        return profiles.get(player.getUniqueId());
    }

    private boolean hasLimit() {
        return this.busters.size() >= Config.BUSTER_LIMIT_PER_PLAYER;
    }

    private boolean isRunningIn(Chunk chunk) {
        return profiles.values().stream().anyMatch(profile -> profile.busters.stream()
        .anyMatch(buster -> buster.getChunk() == chunk));
    }

    public void addBuster(Player player,  Chunk chunk) {
        this.busters.removeIf(buster -> !buster.isRunning());

        if(this.hasLimit()) {
            player.sendMessage(Language.ALREADY_RUNNING_MAX_BUSTERS);
            return;
        }

        if(this.isRunningIn(chunk)) {
            player.sendMessage(Language.ALREADY_BUSTING_THIS_CHUNK);
            return;
        }

        ItemUtils.removeOneItem(player);

        BusterData busterData = new BusterData(chunk);

        if(!busterData.calculateBlocks()) {
            player.sendMessage(Language.CHUNK_ALREADY_BUSTED);
            return;
        }

        this.busters.add(busterData);

        player.sendMessage(Language.BUSTER_STARTING.replace("<seconds>",
        String.valueOf(Config.BUSTER_DELAY)));
    }
}
