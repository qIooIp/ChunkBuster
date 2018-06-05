package me.qiooip.buster;

import lombok.Getter;
import me.qiooip.buster.commands.ChunkBusterCommand;
import me.qiooip.buster.manager.BusterManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Buster extends JavaPlugin {

    @Getter private static Buster instance;

    private BusterManager busterManager;

    @Override
    public void onEnable() {
        instance = this;

        this.busterManager = new BusterManager();

        getCommand("chunkbuster").setExecutor(new ChunkBusterCommand());
    }

    @Override
    public void onDisable() {

    }
}
