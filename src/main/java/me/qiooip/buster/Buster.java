package me.qiooip.buster;

import lombok.Getter;
import lombok.Setter;
import me.qiooip.buster.commands.ChunkBusterCommand;
import me.qiooip.buster.config.Config;
import me.qiooip.buster.config.ConfigFile;
import me.qiooip.buster.config.Language;
import me.qiooip.buster.integration.FactionsUUID;
import me.qiooip.buster.integration.IFactions;
import me.qiooip.buster.integration.MassiveFactions;
import me.qiooip.buster.listeners.BlockPlaceListener;
import me.qiooip.buster.manager.BusterManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Buster extends JavaPlugin {

    @Getter private static Buster instance;

    @Setter private ConfigFile config;

    private IFactions factions;
    private BusterManager busterManager;

    @Override
    public void onEnable() {
        if(!this.setupFactions()) {
            getLogger().severe("ChunkBuster - Disabled due to no Factions dependency found!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        instance = this;

        this.config = new ConfigFile("config.yml");
        new Config();
        new Language();

        this.busterManager = new BusterManager();

        Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(), this);

        getCommand("chunkbuster").setExecutor(new ChunkBusterCommand());
    }

    @Override
    public void onDisable() {

    }

    private boolean setupFactions() {
        Plugin plugin = Bukkit.getPluginManager().getPlugin("Factions");

        if(plugin.isEnabled()) {

            String main = plugin.getClass().getSimpleName();

            if(main.equals("P")) {

                this.factions = new FactionsUUID();
                return true;

            } else if(main.equals("Factions")) {

                this.factions = new MassiveFactions();
                return true;

            }
        }

        return false;
    }
}
