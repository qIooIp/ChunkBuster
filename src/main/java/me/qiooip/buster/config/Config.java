package me.qiooip.buster.config;

import me.qiooip.buster.Buster;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashSet;
import java.util.Set;

public class Config {

    public static ItemStack BUSTER_ITEM;
    public static boolean BUSTER_DISABLED_IN_WILDERNESS;
    public static boolean BUSTER_DISABLED_IN_OWN_CLAIM;
    public static int BUSTER_LIMIT_PER_PLAYER;
    public static int BUSTER_DELAY_BEFORE_START;
    public static boolean RETURN_ITEM_ON_CANCEL;
    public static Set<Material> BUSTER_IGNORED_BLOCKS;

    public Config() {
        ConfigFile config = Buster.getInstance().getConfig();

        Material busterMaterial = Material.getMaterial(config.getString("ITEM.MATERIAL"));
        BUSTER_ITEM = new ItemStack(busterMaterial, 1);
        ItemMeta itemMeta = BUSTER_ITEM.getItemMeta();
        itemMeta.setDisplayName(config.getString("ITEM.NAME"));
        itemMeta.setLore(config.getStringList("ITEM.LORE"));
        BUSTER_ITEM.setItemMeta(itemMeta);

        BUSTER_DISABLED_IN_WILDERNESS = config.getBoolean("CHUNK_BUSTER.DISABLED_IN_WILDERNESS");
        BUSTER_DISABLED_IN_OWN_CLAIM = config.getBoolean("CHUNK_BUSTER.DISABLED_IN_OWN_CLAIM");
        BUSTER_LIMIT_PER_PLAYER = config.getInt("CHUNK_BUSTER.LIMIT_PER_PLAYER");
        BUSTER_DELAY_BEFORE_START = config.getInt("CHUNK_BUSTER.DELAY_BEFORE_START");
        RETURN_ITEM_ON_CANCEL = config.getBoolean("RETURN_ITEM_ON_CANCEL");

        BUSTER_IGNORED_BLOCKS = new HashSet<>();
        config.getStringList("CHUNK_BUSTER.IGNORED_BLOCKS").forEach(material -> {
            if(Material.getMaterial(material) == null) return;

            BUSTER_IGNORED_BLOCKS.add(Material.getMaterial(material));
        });
    }
}
