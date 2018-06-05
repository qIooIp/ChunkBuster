package me.qiooip.buster.manager;

import me.qiooip.buster.config.Config;
import me.qiooip.buster.config.Language;
import me.qiooip.buster.utils.ItemUtils;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class BusterProfile {

    private Set<BusterData> busters;

    BusterProfile() {
        this.busters = new HashSet<>();
    }

    private boolean hasLimit() {
        return this.busters.size() >= Config.BUSTER_LIMIT_PER_PLAYER;
    }

    public int getRunning() {
        return this.busters.size();
    }

    public void addBuster(Player player,  BusterData busterData) {
        this.busters.removeIf(buster -> !buster.isRunning());

        if(this.hasLimit()) {
            player.sendMessage(Language.ALREADY_RUNNING_MAX_BUSTERS);
            return;
        }

        ItemUtils.removeOneItem(player);

        busterData.calculateBlocks();
        this.busters.add(busterData);
    }
}
