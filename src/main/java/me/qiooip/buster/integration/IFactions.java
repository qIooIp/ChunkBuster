package me.qiooip.buster.integration;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public interface IFactions {

    boolean hasFaction(Player player);

    boolean isWilderness(Location location);

    boolean isOwnClaim(Player player, Location location);
}
