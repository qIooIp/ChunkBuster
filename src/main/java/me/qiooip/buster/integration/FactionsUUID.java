package me.qiooip.buster.integration;

import com.massivecraft.factions.Board;
import com.massivecraft.factions.FLocation;
import com.massivecraft.factions.FPlayers;
import com.massivecraft.factions.Faction;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class FactionsUUID implements IFactions {

    @Override
    public boolean hasFaction(Player player) {
        return FPlayers.getInstance().getByPlayer(player).hasFaction();
    }

    @Override
    public boolean isWilderness(Location location) {
        return Board.getInstance().getFactionAt(new FLocation(location)).isWilderness();
    }

    @Override
    public boolean isOwnClaim(Player player, Location location) {
        Faction playerFaction = FPlayers.getInstance().getByPlayer(player).getFaction();
        Faction faction = Board.getInstance().getFactionAt(new FLocation(location));

        return !playerFaction.isWilderness() && (playerFaction == faction);
    }
}
