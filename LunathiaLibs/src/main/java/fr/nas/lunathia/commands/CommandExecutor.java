package fr.nas.lunathia.commands;

import fr.nas.lunathia.exception.NoPermissionException;
import fr.nas.lunathia.exception.PlayerNotFoundException;
import org.bukkit.command.CommandSender;

public interface CommandExecutor {
    void execute(CommandSender sender, String[] args) throws NoPermissionException, PlayerNotFoundException;
}
