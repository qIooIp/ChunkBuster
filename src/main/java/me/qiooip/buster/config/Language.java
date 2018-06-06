package me.qiooip.buster.config;

import me.qiooip.buster.Buster;

import java.util.List;

public class Language {

    public static String ALREADY_RUNNING_MAX_BUSTERS;
    public static String ALREADY_BUSTING_THIS_CHUNK;
    public static String CHUNK_ALREADY_BUSTED;
    public static String BUSTER_STARTING;

    public static String NO_PERMISSION;
    public static String INVALID_NUMBER;
    public static String PLAYER_NOT_ONLINE;
    public static String RELOADED_MESSAGE;
    public static String GIVE_MESSAGE;
    public static List<String> COMMAND_USAGE;

    public Language() {
        ConfigFile config = Buster.getInstance().getConfig();

        ALREADY_RUNNING_MAX_BUSTERS = config.getString("ALREADY_RUNNING_MAX_BUSTERS");
        ALREADY_BUSTING_THIS_CHUNK = config.getString("ALREADY_BUSTING_THIS_CHUNK");
        CHUNK_ALREADY_BUSTED = config.getString("CHUNK_ALREADY_BUSTED");
        BUSTER_STARTING = config.getString("BUSTER_STARTING");

        NO_PERMISSION = config.getString("BUSTER_COMMAND.NO_PERMISSION");
        INVALID_NUMBER = config.getString("BUSTER_COMMAND.INVALID_NUMBER");
        PLAYER_NOT_ONLINE = config.getString("BUSTER_COMMAND.PLAYER_NOT_ONLINE");
        RELOADED_MESSAGE = config.getString("BUSTER_COMMAND.RELOADED");
        GIVE_MESSAGE = config.getString("BUSTER_COMMAND.GIVE_MESSAGE");
        COMMAND_USAGE = config.getStringList("BUSTER_COMMAND.USAGE");
    }
}
