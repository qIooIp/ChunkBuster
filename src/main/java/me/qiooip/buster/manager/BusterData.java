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

public class BusterData {

    @Getter private boolean running;

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
    }

    void calculateBlocks() {
        this.running = true;

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
                            if(Config.BUSTER_IGNORED_BLOCKS.contains(block.getType())) continue;
                            if(block.getType() == Material.AIR) continue;

                            this.set.add(block);
                        }
                    }

                    if(!this.set.isEmpty()) blocks.put(y, new HashSet<>(this.set));

                    this.set.clear();
                }
            }

        }.runTaskAsynchronously(Buster.getInstance());

        new BukkitRunnable() {
            @Override
            public void run() {
                startBuster();
            }
        }.runTaskLater(Buster.getInstance(), 100L);
    }

    private void startBuster() {
        this.busterTask = new BukkitRunnable() {

            Iterator<Integer> iterator = blocks.keySet().iterator();
            Set<Block> set;

            @Override
            public void run() {

                if(!this.iterator.hasNext()) {
                    this.cancel();
                    return;
                }

                this.set = blocks.get(this.iterator.next());
                this.set.forEach(block -> block.setType(Material.AIR));
            }

        }.runTaskTimer(Buster.getInstance(), 0L, 10L);

        this.blocks.clear();
        this.running = false;
    }
}
