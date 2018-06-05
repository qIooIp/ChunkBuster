package me.qiooip.buster.listeners;

import me.qiooip.buster.Buster;
import me.qiooip.buster.config.Config;
import me.qiooip.buster.manager.BusterData;
import org.bukkit.Material;
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

        BusterData data = new BusterData(event.getBlockPlaced().getChunk());
        Buster.getInstance().getBusterManager().getProfile(player).addBuster(player, data);
    }
}
