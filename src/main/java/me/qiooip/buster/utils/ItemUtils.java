package me.qiooip.buster.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemUtils {

    public static void removeOneItem(Player player) {
        if(player.getItemInHand().getAmount() > 1) {
            player.getItemInHand().setAmount(player.getItemInHand().getAmount() - 1);
            return;
        }

        player.getInventory().setItemInHand(new ItemStack(Material.AIR));
    }
}
