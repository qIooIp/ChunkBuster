package me.qiooip.buster.manager;

import lombok.Getter;
import me.qiooip.buster.Buster;
import me.qiooip.buster.config.Config;
import me.qiooip.buster.config.Language;
import me.qiooip.buster.utils.ItemUtils;
import org.bukkit.Chunk;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class BusterProfile {

    @Getter private Set<BusterData> busters;

    BusterProfile() {
        this.busters = new HashSet<>();
    }

    private boolean hasLimit() {
        return this.busters.size() >= Config.BUSTER_LIMIT_PER_PLAYER;
    }

    public void addBuster(Player player,  Chunk chunk) {
        if(this.hasLimit()) {
            player.sendMessage(Language.ALREADY_RUNNING_MAX_BUSTERS);
            return;
        }

        if(Buster.getInstance().getBusterManager().isRunningIn(chunk)) {
            player.sendMessage(Language.ALREADY_BUSTING_THIS_CHUNK);
            return;
        }

        ItemUtils.removeOneItem(player);

        BusterData busterData = new BusterData(this, chunk);
        busterData.calculateBlocks();

        this.busters.add(busterData);

        player.sendMessage(Language.BUSTER_STARTING.replace("<seconds>",
        String.valueOf(Config.BUSTER_DELAY)));
    }
}
