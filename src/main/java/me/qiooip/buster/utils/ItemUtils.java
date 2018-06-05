package me.qiooip.buster.utils;

import me.qiooip.buster.Buster;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class ItemUtils {

    public static void updateInventory(Player player) {
        new BukkitRunnable() {

            @Override
            public void run() { player.updateInventory(); }

        }.runTaskLater(Buster.getInstance(), 1L);
    }

    public static void removeOneItem(Player player) {
        if(player.getItemInHand().getAmount() > 1) {
            player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
            return;
        }

        player.getInventory().setItemInHand(new ItemStack(Material.AIR));
    }
}
