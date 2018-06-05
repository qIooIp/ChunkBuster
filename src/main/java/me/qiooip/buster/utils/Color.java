package me.qiooip.buster.utils;

import org.bukkit.ChatColor;

public class Color {

    public static String translate(String line) {
        return ChatColor.translateAlternateColorCodes('&', line);
    }
}
