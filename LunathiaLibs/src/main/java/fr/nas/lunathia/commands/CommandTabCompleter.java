package fr.nas.lunathia.commands;

import org.bukkit.command.CommandSender;

import java.util.List;

public interface CommandTabCompleter {
    List<String> tabComplete(CommandSender sender, String paramString, String[] args);
}
