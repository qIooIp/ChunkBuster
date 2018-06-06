package me.qiooip.buster;

import lombok.Getter;
import me.qiooip.buster.commands.ChunkBusterCommand;
import me.qiooip.buster.config.Config;
import me.qiooip.buster.config.ConfigFile;
import me.qiooip.buster.config.Language;
import me.qiooip.buster.listeners.BlockPlaceListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Buster extends JavaPlugin {

    @Getter private static Buster instance;

    private ConfigFile config;

    @Override
    public void onEnable() {
        instance = this;

        this.config = new ConfigFile("config.yml");
        new Config();
        new Language();

        Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(), this);

        getCommand("chunkbuster").setExecutor(new ChunkBusterCommand());
    }

    @Override
    public void onDisable() {

    }
}
