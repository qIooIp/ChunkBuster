package me.qiooip.buster.config;

import me.qiooip.buster.Buster;

import java.util.List;

public class Language {

    public static String ALREADY_RUNNING_MAX_BUSTERS;
    public static String ALREADY_BUSTING_THIS_CHUNK;
    public static String BUSTER_STARTING;
    public static String NO_FACTION;
    public static String DISABLED_IN_OWN_CLAIM;
    public static String DISABLED_IN_WILDERNESS;

    public static String FOR_PLAYER_USE_ONLY;
    public static String NO_PERMISSION;
    public static String INVALID_NUMBER;
    public static String PLAYER_NOT_ONLINE;
    public static String RELOADED_MESSAGE;
    public static String GIVE_MESSAGE;
    public static String CANCEL_MESSAGE;
    public static String NO_BUSTERS_RUNNING;
    public static List<String> COMMAND_USAGE;

    public Language() {
        ConfigFile config = Buster.getInstance().getConfig();

        ALREADY_RUNNING_MAX_BUSTERS = config.getString("ALREADY_RUNNING_MAX_BUSTERS");
        ALREADY_BUSTING_THIS_CHUNK = config.getString("ALREADY_BUSTING_THIS_CHUNK");
        BUSTER_STARTING = config.getString("BUSTER_STARTING");
        NO_FACTION = config.getString("NO_FACTION");
        DISABLED_IN_OWN_CLAIM = config.getString("DISABLED_IN_OWN_CLAIM");
        DISABLED_IN_WILDERNESS = config.getString("DISABLED_IN_WILDERNESS");

        FOR_PLAYER_USE_ONLY = config.getString("BUSTER_COMMAND.FOR_PLAYER_USE_ONLY");
        NO_PERMISSION = config.getString("BUSTER_COMMAND.NO_PERMISSION");
        INVALID_NUMBER = config.getString("BUSTER_COMMAND.INVALID_NUMBER");
        PLAYER_NOT_ONLINE = config.getString("BUSTER_COMMAND.PLAYER_NOT_ONLINE");
        RELOADED_MESSAGE = config.getString("BUSTER_COMMAND.RELOADED");
        GIVE_MESSAGE = config.getString("BUSTER_COMMAND.GIVE_MESSAGE");
        CANCEL_MESSAGE = config.getString("BUSTER_COMMAND.CANCEL_MESSAGE");
        NO_BUSTERS_RUNNING = config.getString("BUSTER_COMMAND.NO_BUSTERS_RUNNING");
        COMMAND_USAGE = config.getStringList("BUSTER_COMMAND.USAGE");
    }
}
