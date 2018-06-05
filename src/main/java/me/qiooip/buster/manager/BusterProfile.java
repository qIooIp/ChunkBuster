package me.qiooip.buster.manager;

import me.qiooip.buster.config.Config;

import java.util.HashSet;
import java.util.Set;

public class BusterProfile {

    private Set<BusterData> busters;

    public BusterProfile() {
        this.busters = new HashSet<>();
    }

    public boolean hasLimit() {
        return this.busters.size() >= Config.BUSTER_LIMIT_PER_PLAYER;
    }

    public int getRunningAmount() {
        return this.busters.size();
    }

    public void addBuster(BusterData busterData) {
        if(this.hasLimit()) {

            return;
        }

        this.busters.add(busterData);
    }
}
