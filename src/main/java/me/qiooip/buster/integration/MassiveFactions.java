package me.qiooip.buster.integration;

import com.massivecraft.factions.entity.BoardColl;
import com.massivecraft.factions.entity.Faction;
import com.massivecraft.factions.entity.MPlayer;
import com.massivecraft.massivecore.ps.PS;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class MassiveFactions implements IFactions {

    @Override
    public boolean hasFaction(Player player) {
        return MPlayer.get(player).hasFaction();
    }

    @Override
    public boolean isWilderness(Location location) {
        return BoardColl.get().getFactionAt(PS.valueOf(location)).isNone();
    }

    @Override
    public boolean isOwnClaim(Player player, Location location) {
        Faction playerFaction = MPlayer.get(player).getFaction();
        Faction faction = BoardColl.get().getFactionAt(PS.valueOf(location));

        return !playerFaction.isNone() && (playerFaction == faction);
    }
}
