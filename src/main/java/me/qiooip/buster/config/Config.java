package me.qiooip.buster.config;

import me.qiooip.buster.Buster;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Set;

public class Config {

    public static ItemStack BUSTER_ITEM;
    public static int BUSTER_LIMIT_PER_PLAYER;
    public static Set<Material> BUSTER_IGNORE_BLOCKS;

    public Config() {
        ConfigFile config = Buster.getInstance().getConfig();

        Material busterMaterial = Material.getMaterial(config.getString("ITEM.MATERIAL"));
        BUSTER_ITEM = new ItemStack(busterMaterial);
        ItemMeta itemMeta = BUSTER_ITEM.getItemMeta();
        itemMeta.setDisplayName(config.getString("ITEM.NAME"));
        itemMeta.setLore(config.getStringList("ITEM.LORE"));
        BUSTER_ITEM.setItemMeta(itemMeta);

        BUSTER_LIMIT_PER_PLAYER = config.getInt("CHUNK_BUSTER.LIMIT_PER_PLAYER");

        config.getStringList("CHUNK_BUSTER.IGNORE_BLOCKS").forEach(material -> {
            if(Material.getMaterial(material) == null) return;

            BUSTER_IGNORE_BLOCKS.add(Material.getMaterial(material));
        });
    }
}
