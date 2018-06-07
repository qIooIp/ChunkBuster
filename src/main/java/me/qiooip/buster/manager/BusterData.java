package me.qiooip.buster.manager;

import lombok.Getter;
import me.qiooip.buster.Buster;
import me.qiooip.buster.config.Config;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

class BusterData {

    private BusterProfile profile;

    private BukkitTask calculationTask;
    private BukkitTask busterTask;
    private BukkitTask delayTask;

    @Getter private Chunk chunk;
    private int minX;
    private int minZ;

    private Map<Integer, Set<Block>> blocks;

    BusterData(BusterProfile profile, Chunk chunk) {
        this.profile = profile;

        this.chunk = chunk;
        this.minX = chunk.getX() << 4;
        this.minZ = chunk.getZ() << 4;

        this.blocks = new LinkedHashMap<>();
    }

    void cancelTasks() {
        if(this.calculationTask != null) this.calculationTask.cancel();
        if(this.busterTask != null) this.busterTask.cancel();
        if(this.delayTask != null) this.delayTask.cancel();
    }

    void calculateBlocks() {
        this.calculationTask = new BukkitRunnable() {

            Set<Block> set = new HashSet<>();

            @Override
            public void run() {

                for(int y = 256; y >= 0; y--) {

                    if(y <= 0) {
                        this.cancel();
                        return;
                    }

                    for(int x = minX; x < minX + 16; x++) {
                        for(int z = minZ; z < minZ + 16; z++) {
                            Block block = chunk.getBlock(x, y, z);

                            if(Config.BUSTER_IGNORED_BLOCKS.contains(block.getType()) ||
                                (block.getType() == Material.AIR)) continue;

                            this.set.add(block);
                        }
                    }

                    if(!this.set.isEmpty()) blocks.put(y, new HashSet<>(this.set));

                    this.set.clear();
                }
            }

        }.runTaskAsynchronously(Buster.getInstance());

        this.delayTask = new BukkitRunnable() {
            @Override
            public void run() {
                startBuster();
            }
        }.runTaskLaterAsynchronously(Buster.getInstance(), Config.BUSTER_DELAY_BEFORE_START * 20L);
    }

    private void startBuster() {
        this.busterTask = new BukkitRunnable() {

            Iterator<Set<Block>> iterator = blocks.values().iterator();

            @Override
            public void run() {
                if(!this.iterator.hasNext()) {
                    profile.getBusters().remove(BusterData.this);
                    this.cancel();
                    return;
                }

                this.iterator.next().forEach(block -> block.setType(Material.AIR, false));
                this.iterator.remove();
            }

        }.runTaskTimer(Buster.getInstance(), 0L, 10L);
    }
}
