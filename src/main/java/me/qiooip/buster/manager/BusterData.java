package me.qiooip.buster.manager;

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

public class BusterData {

    private BukkitTask calculationTask;
    private BukkitTask busterTask;

    private Chunk chunk;
    private int minX;
    private int minZ;

    private Map<Integer, Set<Block>> blocks;

    public BusterData(Chunk chunk) {
        this.chunk = chunk;
        this.minX = chunk.getX() << 4;
        this.minZ = chunk.getZ() << 4;

        this.blocks = new LinkedHashMap<>();

        this.calculateBlocks();
    }

    public boolean isRunning() {
        return this.calculationTask != null || this.busterTask != null;
    }

    private void calculateBlocks() {
        this.calculationTask = new BukkitRunnable() {

            Set<Block> set = new HashSet<>();

            @Override
            public void run() {

                for(int y = 256; y >= 0; y--) {

                    if(y <= 0) this.cancel();

                    for(int x = minX, z = minZ; x < minX + 16 && z < minZ + 16; x++, z++) {
                        this.set.add(chunk.getBlock(x, y, z));
                    }

                    if(!this.set.isEmpty()) blocks.put(y, set);

                    this.set.clear();
                }

            }

        }.runTaskAsynchronously(Buster.getInstance());

        this.startBuster();
    }

    private void startBuster() {
        this.busterTask = new BukkitRunnable() {

            Iterator<Integer> iterator = blocks.keySet().iterator();
            Set<Block> set;

            @Override
            public void run() {

                if(!this.iterator.hasNext()) this.cancel();

                this.set = blocks.get(this.iterator.next());

                this.set.stream().filter(block -> !Config.BUSTER_IGNORE_BLOCKS.contains(block.getType()))
                .forEach(block -> block.setType(Material.AIR));
            }

        }.runTaskTimer(Buster.getInstance(), 0L, 10L);
    }
}
