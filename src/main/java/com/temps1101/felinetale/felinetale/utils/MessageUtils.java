package com.temps1101.felinetale.felinetale.utils;

import java.text.MessageFormat;
import org.bukkit.ChatColor;

public class MessageUtils {
    public static String message(String input, Boolean is_prefix, Object... args) {
        String PREFIX = "&7[&3FelineTale&7] &r";

        if (is_prefix) {
            return MessageFormat.format(ChatColor.translateAlternateColorCodes('&', PREFIX + input), args);
        } else {
            return MessageFormat.format(ChatColor.translateAlternateColorCodes('&', input), args);
        }
    }
}
