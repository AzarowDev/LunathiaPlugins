package fr.nas.lunathia.utils;

import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static final Pattern HEX_PATTERN = Pattern.compile("(#[A-Fa-f0-9]{6})");

    public static String color(String string) {
        Matcher matcher = HEX_PATTERN.matcher(string);

        while (matcher.find()) {
            string = string.replace(matcher.group(), "" + net.md_5.bungee.api.ChatColor.of(matcher.group()));
        }
        return ChatColor.translateAlternateColorCodes('&', string);
    }
}
