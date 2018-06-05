package me.qiooip.buster.config;

import me.qiooip.buster.Buster;

import java.util.List;

public class Language {

    public static String NO_PERMISSION;
    public static String INVALID_NUMBER;
    public static String PLAYER_NOT_ONLINE;
    public static List<String> COMMAND_USAGE;

    public Language() {
        ConfigFile config = Buster.getInstance().getConfig();

        NO_PERMISSION = config.getString("BUSTER_COMMAND.NO_PERMISSION");
        INVALID_NUMBER = config.getString("BUSTER_COMMAND.INVALID_NUMBER");
        PLAYER_NOT_ONLINE = config.getString("BUSTER_COMMAND.PLAYER_NOT_ONLINE");
        COMMAND_USAGE = config.getStringList("BUSTER_COMMAND.USAGE");
    }
}
