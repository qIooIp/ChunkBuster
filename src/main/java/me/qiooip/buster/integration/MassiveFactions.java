package me.qiooip.buster.integration;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MassiveFactions implements IFactions {

    @Override
    public boolean hasFaction(Player player) {
        return false;
    }

    @Override
    public boolean isWilderness(Location location) {
        return false;
    }

    @Override
    public boolean isOwnClaim(Player player, Location location) {
        return false;
    }
}
