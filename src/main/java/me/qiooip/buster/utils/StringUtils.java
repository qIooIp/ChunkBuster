package me.qiooip.buster.utils;

public class StringUtils {

    public static boolean isNumber(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
}
