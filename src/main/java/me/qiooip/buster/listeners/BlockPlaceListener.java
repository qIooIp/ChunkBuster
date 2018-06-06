package me.qiooip.buster.listeners;

import me.qiooip.buster.Buster;
import me.qiooip.buster.config.Config;
import me.qiooip.buster.config.Language;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

public class BlockPlaceListener implements Listener {

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        if(event.isCancelled()) return;

        Player player = event.getPlayer();

        ItemStack item = event.getItemInHand();
        if(item == null || item.getType() == Material.AIR) return;
        if(!item.hasItemMeta()) return;
        if(!item.getItemMeta().getDisplayName().equals(Config.BUSTER_ITEM.getItemMeta().getDisplayName())) return;

        event.setCancelled(true);

        if(!Buster.getInstance().getFactions().hasFaction(player)) {
            player.sendMessage(Language.NO_FACTION);
            return;
        }

        Block block = event.getBlockPlaced();

        if(!Buster.getInstance().getFactions().isOwnClaim(player, block.getLocation())) {
            player.sendMessage(Language.NOT_IN_OWN_CLAIM_OR_WILDERNESS);
            return;
        }

        Buster.getInstance().getBusterManager().getProfile(player)
        .addBuster(player, block.getChunk());
    }
}
